# spring-boot-user-salary-api
Spring Boot User Salary API

# API
### To upload CSV file
* POST
```
localhost:8080/govtech/api/v1/users/upload
```
* CSV Example Format
```
name,salary
John,2500.05
Mary Posa, 4000.00
Mike,4001.00
```


### To Get Users with salary between 0 to 4000
* GET
```
localhost:8080/govtech/api/v1/users
```

## To upload CSV file
* POST
```
localhost:8080/govtech/api/v1/users/upload
```
* CSV Example
```
name,salary
John,2500.05
Mary Posa, 4000.00
Mike,4001.00
```

## Built With

* Gradle
* Spring Boot
  * Spring Data JPA
* Jackson (To read csv)
* Lombok
* SQL Server 2017
* Apache Tomcat


### SQL Server Create Table Script 
```
USE [govtech]
GO

/****** Object:  Table [dbo].[user_salary]    Script Date: 5/19/2019 2:20:35 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user_salary](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[salary] [decimal](18, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

```

# To Deploy
* Ensure that SQL Server is configured properly
* Download and Extract Tomcat http://mirror.23media.de/apache/tomcat/tomcat-8/v8.5.30/bin/
* Navigate to Tomcat Webapps folder and paste govtech.war file
* Open cmd and change to Tomcat bin folder
* run ```service install```
* run ```catalina.bat run```



