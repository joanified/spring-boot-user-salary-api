# spring-boot-user-salary-api
Spring Boot User Salary API

## Built With

* Gradle
* Spring Boot
  * Spring Data JPA
* Jackson (To read csv)
* Lombok
* SQL Server 2017


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

