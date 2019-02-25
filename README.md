# lucky-project
提供基于spring boot的通用模块，如：Security安全管理，支付等，使用项目开发更快速

一、该项目提供了哪些功能？？

   整个项目封装了Security安全管理、微信和支付宝支付模块，只需要要在你的项目中引入相应的依赖，就可以实现相应的功能。
当项目提供的功能不满足你的需求时，可以实现相应的接口，进行个性化实现。


   1、基于Seurity的安全管理，功能如下：
   
      1)  登录认证
      
            用户名+密码+验证登录认证    手机号+手机短信登录认证   三方登录认证（微信、QQ）
            
     浏览器           满足                        满足                 满足
  
     APP              满足                        满足                 满足
   
      以上是项目支持的认证方式，若以上认证方式无法满足你的需求，可以实现相应的接口即可（无须配置，项目在启动时会自动扫描你的实现的接口并将它配置在项目中）
      
    2） 项目为B/S架构时，提供了SESSION管理
    
        2-1  session的存贮
        2-2  session过期和失效的处理
        
    3） 项目为C/S架构时，提供了令牌的管理
    
        3-1  令牌的配置（生成方式、过期时间等）
        
    4） 权限管理
         项目提供了一整套的RBAC模型，满足基本的开发需求，
       
     
   2、常用的支付
    
       项目提供的支付方式如下：
                 
                   电脑网站支付        手机网站支付     
                   
       支付宝：        支持               支持           
       
       
                                       js支付/H5支付
       微信 ：         支持                支持          
       
       
       银联 ：         支持                 支持        
       
二、怎么使用以及怎么去个性化自己的需求

     整个模块的结构及说明如下:
       lucky-project-parent   ---------------父项目
         --commom     -----------------------通用模块，主要是放工具类和常用的通用配置                  
           --lucky-project-core     -----------核心代码  依赖commom模块
             --lucky-project-app     ------------跟APP有关的代码  依赖lucky-project-core模块
               --lucky-project-demo-app     -------app的测试demo  依赖lucky-project-app模块
             --lucky-project-browser     --------跟浏览器有关的代码  依赖lucky-project-core模块
               --lucky-project-demo-browser     ---浏览器的测试demo  依赖lucky-project-browser 模块
         pom.xml     -----------------------父项目的pom文件
       
       
    1、怎么使用
       
        若项目是浏览器项目,加入 lucky-project-browser依赖即可，若项目是app项目,加入 lucky-project-app依赖即可
        
        使用方式参考lucky-project-demo-browser模块的com.lucky.demo.browser.pay。
       
       支付宝支付的配置项：
       lucky:
         pay:
           ali:
             appid: 支付宝的appid
             way-url: 支付宝的支付网关
             alipay-public-key: 支付宝公钥
             merchant-private-key: 商户私钥
             
           

        
        
             
             
                         
       
        
