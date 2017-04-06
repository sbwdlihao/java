## Security

### 客户端通过https访问服务端
需要掌握[keytool](https://docs.oracle.com/cd/E19509-01/820-3503/jcapsconfssls_intro/index.html)的使用

#### 创建密钥对
```
keytool -genkeypair -alias serverdev1 -keyalg RSA -keysize 2048 -keypass devtest -keystore dev.jks -storepass devtest

您的名字与姓氏是什么?
  [Unknown]:  localhost
您的组织单位名称是什么?
  [Unknown]:  my
您的组织名称是什么?
  [Unknown]:  my
您所在的城市或区域名称是什么?
  [Unknown]:  hz
您所在的省/市/自治区名称是什么?
  [Unknown]:  zj
该单位的双字母国家/地区代码是什么?
  [Unknown]:  cn
CN=localhost, OU=my, O=my, L=hz, ST=zj, C=cn是否正确?
  [否]:  y
```
如果keystore不存在会自动创建

*注意* 在选择姓和名时输入本机的名字，否则在本地客户端访问时会通不过host name verify

#### 导出证书
```
keytool -exportcert  -keystore dev.jks -storepass devtest -alias serverdev1 -rfc -file serverdev1.cert
```

#### 设置spring boot的https
```
server.port=8443
server.ssl.key-store=classpath:dev.jks
server.ssl.key-store-password=devtest
server.ssl.key-alias=serverdev1
server.ssl.key-password=devtest
```

#### 客户端访问
```java
System.setProperty("javax.net.ssl.trustStore", getClass().getClassLoader().getResource("dev.jks").getPath());
System.setProperty("javax.net.ssl.trustStorePassword", "devtest");
```
可以通过属性设置trustStore