# Manufacturing-Execution-System-Team3
# 프로젝트 실행 가이드

이 프로젝트는 **Spring Boot 백엔드**, **Vue 프론트엔드**, **Python PLC 프로그램**으로 구성되어 있으며, Kafka와 MySQL과 연동됩니다.  
Windows 환경에서 localhost 기준으로 실행합니다.

---

## 1. 환경/도구 설치

| 도구 | 권장 버전 | 설치 링크 / 설명 |
|------|-----------|----------------|
| Java JDK | 17 이상 | [다운로드](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| Maven | Bundled (mvnw 사용) | 별도 설치 필요 없음 |
| Node.js / npm | 18 이상 | [다운로드](https://nodejs.org/) |
| Python | 3.10 이상 | [다운로드](https://www.python.org/downloads/windows/) |
| pip | 최신 | Python 패키지 설치 |
| Git | 최신 (선택) | [다운로드](https://git-scm.com/) |
| Docker | 최신 | [다운로드](https://www.docker.com/) |

💡 Windows PowerShell에서 권한 설정:
```powershell
git clone https://github.com/Hyundai-Autoever-Smart-Factory-2nd/Manufacturing-Execution-System-Team3.git
cd Manufacturing-Execution-System-Team3
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
.\start_all.ps1
