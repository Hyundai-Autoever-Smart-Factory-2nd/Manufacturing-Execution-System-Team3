<template>
  <div ref="sceneContainer" class="w-full h-screen bg-gradient-to-b from-slate-900 to-slate-700"></div>
</template>

<script setup>
import * as THREE from 'three';
import { onMounted, ref } from 'vue';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';

const sceneContainer = ref(null);

// 텍스트 생성 함수
function createTextTexture(text, fontSize = 64, color = '#ffffff') {
  const canvas = document.createElement('canvas');
  const context = canvas.getContext('2d');
  canvas.width = 512;
  canvas.height = 128;
  
  context.fillStyle = '#000000';
  context.fillRect(0, 0, canvas.width, canvas.height);
  
  context.font = `bold ${fontSize}px Arial, sans-serif`;
  context.fillStyle = color;
  context.textAlign = 'center';
  context.textBaseline = 'middle';
  context.fillText(text, canvas.width / 2, canvas.height / 2);
  
  const texture = new THREE.CanvasTexture(canvas);
  return texture;
}

onMounted(() => {
  const scene = new THREE.Scene();
  scene.background = new THREE.Color(0xf8f9fa);
  scene.fog = new THREE.Fog(0xf8f9fa, 30, 100);

  const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
  camera.position.set(0, 15, 25);

  const renderer = new THREE.WebGLRenderer({ antialias: true });
  renderer.setSize(window.innerWidth, window.innerHeight);
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = THREE.PCFSoftShadowMap;
  sceneContainer.value.appendChild(renderer.domElement);

  const controls = new OrbitControls(camera, renderer.domElement);
  controls.enableDamping = true;
  controls.dampingFactor = 0.05;

  // 바닥
  const floorGeometry = new THREE.PlaneGeometry(40, 20);
  const floorMaterial = new THREE.MeshLambertMaterial({ color: 0xe9ecef, transparent: true, opacity: 0.8 });
  const floor = new THREE.Mesh(floorGeometry, floorMaterial);
  floor.rotation.x = -Math.PI / 2;
  floor.position.y = -2;
  floor.receiveShadow = true;
  scene.add(floor);

  // 조명
  const ambientLight = new THREE.AmbientLight(0x404040, 0.6);
  scene.add(ambientLight);
  
  const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
  directionalLight.position.set(10, 20, 10);
  directionalLight.castShadow = true;
  directionalLight.shadow.mapSize.width = 2048;
  directionalLight.shadow.mapSize.height = 2048;
  scene.add(directionalLight);

  const pointLight = new THREE.PointLight(0x74b9ff, 0.3, 30);
  pointLight.position.set(0, 10, 0);
  scene.add(pointLight);

  // === 1. 프레스 공정 ===
  const pressFrame = new THREE.Mesh(
    new THREE.BoxGeometry(4, 6, 3),
    new THREE.MeshStandardMaterial({ color: 0x495057,
         color: 0xffffff,   // 흰색
        transparent: true, // 투명 허용
        opacity: 0.2,      // 투명도 조절 (0~1)
        metalness: 0.3, roughness: 0.4 })
  );
  pressFrame.position.set(-12, 1, 0);
  pressFrame.castShadow = true;
  scene.add(pressFrame);

  const pressTop = new THREE.Mesh(
    new THREE.BoxGeometry(2.5, 0.8, 2.5),
    new THREE.MeshStandardMaterial({ color: 0xff6b6b, metalness: 0.4, roughness: 0.3 })
  );
  const pressBottom = new THREE.Mesh(
    new THREE.BoxGeometry(2.5, 0.8, 2.5),
    new THREE.MeshStandardMaterial({ color: 0xff5252, metalness: 0.4, roughness: 0.3 })
  );
  pressTop.position.set(-12, 3.5, 0);
  pressBottom.position.set(-12, 0.5, 0);
  pressTop.castShadow = true;
  pressBottom.castShadow = true;
  scene.add(pressTop, pressBottom);

  // 프레스 라벨
  const pressLabelTexture = createTextTexture('프레스', 48, '#ff6b6b');
  const pressLabel = new THREE.Mesh(
    new THREE.PlaneGeometry(4, 1),
    new THREE.MeshBasicMaterial({ map: pressLabelTexture, transparent: true, alphaTest: 0.1 })
  );
  pressLabel.position.set(-12, 6, 0);
  scene.add(pressLabel);

  // === 2. 용접 공정 ===
  const weldingBase = new THREE.Mesh(
    new THREE.CylinderGeometry(2, 2, 1, 32),
    new THREE.MeshStandardMaterial({ color: 0x00ff00, metalness: 0.6, roughness: 0.3 })
  );
  weldingBase.position.set(-4, -1, 0);
  weldingBase.castShadow = true;
  scene.add(weldingBase);

  const weldingTarget = new THREE.Mesh(
    new THREE.BoxGeometry(1.5, 1.5, 1.5),
    new THREE.MeshStandardMaterial({ color: 0x0000ff, metalness: 0.5, roughness: 0.4 })
  );
  weldingTarget.position.set(-4, 1, 0);
  weldingTarget.castShadow = true;
  scene.add(weldingTarget);

  const weldingRobotArm = new THREE.Mesh(
    new THREE.CylinderGeometry(0.3, 0.3, 4, 16),
    new THREE.MeshStandardMaterial({ color: 0x26de81, metalness: 0.5, roughness: 0.4 })
  );
  weldingRobotArm.position.set(-4, 4, 0);
  weldingRobotArm.castShadow = true;
  scene.add(weldingRobotArm);

  const sparkGeometry = new THREE.BufferGeometry();
  const sparkPositions = [];
  for (let i = 0; i < 50; i++) {
    sparkPositions.push((Math.random() - 0.5) * 2);
    sparkPositions.push((Math.random() - 0.5) * 2);
    sparkPositions.push((Math.random() - 0.5) * 2);
  }
  sparkGeometry.setAttribute('position', new THREE.Float32BufferAttribute(sparkPositions, 3));
  
  const weldingSpark = new THREE.Points(
    sparkGeometry,
    new THREE.PointsMaterial({ color: 0xffaa00, size: 0.1, blending: THREE.AdditiveBlending })
  );
  weldingSpark.visible = false;
  scene.add(weldingSpark);

  const weldingLabelTexture = createTextTexture('용접', 48, '#4ecdc4');
  const weldingLabel = new THREE.Mesh(
    new THREE.PlaneGeometry(4, 1),
    new THREE.MeshBasicMaterial({ map: weldingLabelTexture, transparent: true, alphaTest: 0.1 })
  );
  weldingLabel.position.set(-4, 6, 0);
  scene.add(weldingLabel);

  // === 3. 도장 공정 ===
  const paintingStation = new THREE.Mesh(
    new THREE.CylinderGeometry(2.5, 2.5, 3.5, 32),
    new THREE.MeshStandardMaterial({ color: 0x9c88ff, transparent: true, opacity: 0.7, metalness: 0.2, roughness: 0.6 })
  );
  paintingStation.position.set(4, 1.25, 0);
  paintingStation.castShadow = true;
  scene.add(paintingStation);

  const paintingBox = new THREE.Mesh(
    new THREE.BoxGeometry(1.8, 1.8, 1.8),
    new THREE.MeshStandardMaterial({ color: 0xf1f3f4, metalness: 0.1, roughness: 0.8 })
  );
  paintingBox.position.set(4, -0.5, 0);
  paintingBox.castShadow = true;
  scene.add(paintingBox);

  const paintingLabelTexture = createTextTexture('도장', 48, '#9c88ff');
  const paintingLabel = new THREE.Mesh(
    new THREE.PlaneGeometry(4, 1),
    new THREE.MeshBasicMaterial({ map: paintingLabelTexture, transparent: true, alphaTest: 0.1 })
  );
  paintingLabel.position.set(4, 4.5, 0);
  scene.add(paintingLabel);

  // === 4. 조립 공정 ===
  const assemblyBase = new THREE.Mesh(
    new THREE.BoxGeometry(5, 0.5, 4),
    new THREE.MeshStandardMaterial({ color: 0x495057, metalness: 0.4, roughness: 0.4 })
  );
  assemblyBase.position.set(12, -1.5, 0);
  assemblyBase.castShadow = true;
  scene.add(assemblyBase);

  const assemblyBoxLeft = new THREE.Mesh(
    new THREE.BoxGeometry(1.8, 1.8, 1.8),
    new THREE.MeshStandardMaterial({ color: 0x45aaf2, metalness: 0.3, roughness: 0.5 })
  );
  const assemblyBoxRight = new THREE.Mesh(
    new THREE.BoxGeometry(1.8, 1.8, 1.8),
    new THREE.MeshStandardMaterial({ color: 0x2d98da, metalness: 0.3, roughness: 0.5 })
  );
  assemblyBoxLeft.position.set(12, 1, -2);
  assemblyBoxRight.position.set(12, 1, 2);
  assemblyBoxLeft.castShadow = true;
  assemblyBoxRight.castShadow = true;
  scene.add(assemblyBoxLeft, assemblyBoxRight);

  const assemblyLabelTexture = createTextTexture('조립', 48, '#45aaf2');
  const assemblyLabel = new THREE.Mesh(
    new THREE.PlaneGeometry(4, 1),
    new THREE.MeshBasicMaterial({ map: assemblyLabelTexture, transparent: true, alphaTest: 0.1 })
  );
  assemblyLabel.position.set(12, 6, 0);
  scene.add(assemblyLabel);

  // 애니메이션 변수
  let pressDir = -0.03;
  let weldingArmDir = -0.03;
  let paintingBoxDir = 0.02;
  let assemblyStep = 0;
  let time = 0;

  function animate() {
    requestAnimationFrame(animate);
    time += 0.01;

    // === 프레스: 빨간 직육면체 2개 상하 운동 ===
    pressTop.position.y += pressDir;
    pressBottom.position.y -= pressDir;
    if (pressTop.position.y <= 1.5 || pressTop.position.y >= 3.5) {
      pressDir *= -1;
    }

    // === 용접 애니메이션 ===
    weldingTarget.rotation.x += 0.02;
    weldingTarget.rotation.y += 0.03;
    weldingRobotArm.position.y += weldingArmDir;
    if (weldingRobotArm.position.y <= 2 || weldingRobotArm.position.y >= 4) {
      weldingArmDir *= -1;
      weldingSpark.position.set(weldingRobotArm.position.x, weldingTarget.position.y + 1, weldingRobotArm.position.z);
      weldingSpark.visible = true;
      setTimeout(() => weldingSpark.visible = false, 200);
    }

    // === 도장 애니메이션 ===
    paintingBox.position.y += paintingBoxDir;
    if (paintingBox.position.y >= 3) {
      paintingBoxDir *= -1;
      paintingBox.material.color.set(0xf1f3f4);
    } else if (paintingBox.position.y <= -0.5) {
      paintingBoxDir *= -1;
      paintingBox.material.color.set(0x9c88ff);
    }

    // === 조립 애니메이션 ===
    if (assemblyStep < 100) {
      assemblyBoxLeft.position.z += 0.02;
      assemblyBoxRight.position.z -= 0.02;
      assemblyStep++;
      if (assemblyStep >= 100) {
        setTimeout(() => {
          assemblyBoxLeft.position.z = -2;
          assemblyBoxRight.position.z = 2;
          assemblyStep = 0;
        }, 2000);
      }
    }

    // 라벨 카메라 향하게
    [pressLabel, weldingLabel, paintingLabel, assemblyLabel].forEach(label => {
      label.lookAt(camera.position);
    });

    controls.update();
    renderer.render(scene, camera);
  }

  animate();

  window.addEventListener('resize', () => {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
  });
});
</script>

<style>
html, body, #app, div {
  margin: 0;
  padding: 0;
  overflow: hidden;
  font-family: 'Arial', sans-serif;
}
</style>
