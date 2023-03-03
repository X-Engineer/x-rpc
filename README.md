# X-RPC
## 项目简介
手写 RPC 框架实战

## 模块介绍
- x-rpc-client：客户端模块
- x-rpc-codec：序列化模块
- x-rpc-common：公共方法的模块
- x-rpc-proto：通信协议模块
- x-rpc-server：服务端模块，对服务做管理
- x-rpc-transport：网络通信模块

## 调用链路

- cn.nzcer.example.main 

~~~mermaid
sequenceDiagram
actor User
User ->> Server : main
activate Server
Server ->> RpcServer : new
activate RpcServer
RpcServer ->> RpcServerConfig : new
activate RpcServerConfig
RpcServerConfig -->> RpcServer : #32; 
deactivate RpcServerConfig
RpcServer ->> RpcServer : new
activate RpcServer
RpcServer ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcServer : #32; 
deactivate ReflectionUtils
RpcServer ->> TransportServer : init
activate TransportServer
TransportServer -->> RpcServer : #32; 
deactivate TransportServer
RpcServer ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcServer : #32; 
deactivate ReflectionUtils
RpcServer ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcServer : #32; 
deactivate ReflectionUtils
RpcServer ->> ServiceManager : new
activate ServiceManager
ServiceManager -->> RpcServer : #32; 
deactivate ServiceManager
RpcServer -->> RpcServer : #32; 
deactivate RpcServer
RpcServer -->> Server : #32; 
deactivate RpcServer
Server ->> RpcServer : register
activate RpcServer
RpcServer ->> ServiceManager : register
activate ServiceManager
ServiceManager ->> ReflectionUtils : getPublicMethods
activate ReflectionUtils
ReflectionUtils -->> ServiceManager : #32; 
deactivate ReflectionUtils
ServiceManager ->> ServiceDescriptor : from
activate ServiceDescriptor
ServiceDescriptor ->> ServiceDescriptor : new
activate ServiceDescriptor
ServiceDescriptor -->> ServiceDescriptor : #32; 
deactivate ServiceDescriptor
ServiceDescriptor -->> ServiceManager : #32; 
deactivate ServiceDescriptor
ServiceManager ->> ServiceInstance : new
activate ServiceInstance
ServiceInstance -->> ServiceManager : #32; 
deactivate ServiceInstance
ServiceManager -->> RpcServer : #32; 
deactivate ServiceManager
RpcServer -->> Server : #32; 
deactivate RpcServer
Server ->> RpcServer : start
activate RpcServer
RpcServer ->> TransportServer : start
activate TransportServer
TransportServer -->> RpcServer : #32; 
deactivate TransportServer
RpcServer -->> Server : #32; 
deactivate RpcServer
deactivate Server
~~~



- cn.nzcer.example.Client

~~~mermaid
sequenceDiagram
actor User
User ->> Client : main
activate Client
Client ->> RpcClient : new
activate RpcClient
RpcClient ->> RpcClientConfig : new
activate RpcClientConfig
RpcClientConfig -->> RpcClient : #32; 
deactivate RpcClientConfig
RpcClient ->> RpcClient : new
activate RpcClient
RpcClient ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcClient : #32; 
deactivate ReflectionUtils
RpcClient ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcClient : #32; 
deactivate ReflectionUtils
RpcClient ->> ReflectionUtils : newInstance
activate ReflectionUtils
ReflectionUtils -->> RpcClient : #32; 
deactivate ReflectionUtils
RpcClient ->> TransportSelector : init
activate TransportSelector
TransportSelector -->> RpcClient : #32; 
deactivate TransportSelector
RpcClient -->> RpcClient : #32; 
deactivate RpcClient
RpcClient -->> Client : #32; 
deactivate RpcClient
Client ->> RpcClient : getProxy
activate RpcClient
RpcClient ->> RemoteInvoker : new
activate RemoteInvoker
RemoteInvoker -->> RpcClient : #32; 
deactivate RemoteInvoker
RpcClient -->> Client : #32; 
deactivate RpcClient
Client ->> CalcService : add
activate CalcService
CalcService -->> Client : #32; 
deactivate CalcService
Client ->> CalcService : minus
activate CalcService
CalcService -->> Client : #32; 
deactivate CalcService
deactivate Client
~~~



## 效果

- 启动服务端

![image-20230303125404653](https://raw.githubusercontent.com/zhicheng-ning/Pic-Go/main/md/image-20230303125404653.png)

- 启动客户端

![image-20230303125452404](https://raw.githubusercontent.com/zhicheng-ning/Pic-Go/main/md/image-20230303125452404.png)