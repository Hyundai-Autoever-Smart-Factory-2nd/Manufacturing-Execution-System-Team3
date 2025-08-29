import mysql.connector
from kafka import KafkaProducer
import json
import random
import time

# MySQL 연결
db = mysql.connector.connect(
    host="localhost",
    user="myuser",
    password="secret",
    database="mydatabase"
)
cursor = db.cursor(dictionary=True)

# Kafka Producer 생성
producer = KafkaProducer(
    bootstrap_servers="localhost:9092",
    value_serializer=lambda v: json.dumps(v).encode("utf-8")
)

topic = "machine-sensor"
# 머신별 상태 초기화
machine_state = {}

while True:
    cursor.execute("SELECT machine_id, machine_name, type FROM MACHINE")
    machines = cursor.fetchall()

    for machine in machines:
        mid = machine["machine_id"]
        mtype = machine["type"]

        # 초기값 설정
        if mid not in machine_state:
            if mtype == 1:  # 압력
                machine_state[mid] = random.randint(50, 100)
            elif mtype == 2:  # 온도
                machine_state[mid] = random.randint(40, 60)
            elif mtype == 3:  # 도장 양
                machine_state[mid] = 100
            elif mtype == 4:  # 조립 progress
                machine_state[mid] = 0

        # type별 변화
        if mtype == 1:  # 압력
            delta = random.randint(-2, 2)
            sensor_value = max(0, min(150, machine_state[mid] + delta))
        elif mtype == 2:  # 온도
            delta = random.randint(-1, 1)
            sensor_value = max(20, min(100, machine_state[mid] + delta))
        elif mtype == 3:  # 도장 양
            delta = random.randint(-1, 0)  # 천천히 감소
            sensor_value = max(0, machine_state[mid] + delta)
        elif mtype == 4:  # 조립 progress
            delta = random.randint(0, 2)  # 천천히 증가
            sensor_value = min(100, machine_state[mid] + delta)

        # 상태 업데이트
        machine_state[mid] = sensor_value

        message = {
            "machine_id": mid,
            "name": machine["machine_name"],
            "type": mtype,
            "sensor_value": sensor_value
        }

        producer.send(topic, value=message)
        print("Published:", message)

    time.sleep(2)
