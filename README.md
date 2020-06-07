# demo-server
服务端demo

###开发环境
* OpenJDK版本为1.8.0_252

###启动开发服务
* Windows
```bash
gradlew.bat bootRun
```
* MacOS / Linux
```bash
./gradlew bootRun
```

###编译正式服务
* Windows
```bash
gradlew.bat build
```
* MacOS / Linux
```bash
./gradlew build
```

###编译docker镜像（需要先编译正式服务）

```bash
docker build -t hvlive/demo-server -f ./Dockerfile build/libs
```
