### 第2课
对于异构系统的集成主要采用一层zuul代理：

异构系统通过zuul调用spring cloud服务

spring cloud **直接** 调用异构系统

这套zuul代理的实现叫做sidecar，实验步骤：

开启第1课的所有服务

开启sidecar（zuul代理）

开启phpmock（模拟PHP服务，该项目是一个原始的springboot项目，并未接入spring cloud）

访问http://localhost:8210/php/provider 测试php调用spring cloud

启动sidecar-consumer，这是一个spring cloud服务

访问http://localhost:8220/consumer/php 测试spring cloud调用 php

