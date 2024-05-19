USE [master]
GO
/****** Object:  Database [SHOPPEAR]    Script Date: 5/19/2024 6:53:17 PM ******/
CREATE DATABASE [SHOPPEAR]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SHOPPEAR', FILENAME = N'C:\Users\joaco\SHOPPEAR.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SHOPPEAR_log', FILENAME = N'C:\Users\joaco\SHOPPEAR_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SHOPPEAR] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SHOPPEAR].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SHOPPEAR] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SHOPPEAR] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SHOPPEAR] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SHOPPEAR] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SHOPPEAR] SET ARITHABORT OFF 
GO
ALTER DATABASE [SHOPPEAR] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SHOPPEAR] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SHOPPEAR] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SHOPPEAR] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SHOPPEAR] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SHOPPEAR] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SHOPPEAR] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SHOPPEAR] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SHOPPEAR] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SHOPPEAR] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SHOPPEAR] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SHOPPEAR] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SHOPPEAR] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SHOPPEAR] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SHOPPEAR] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SHOPPEAR] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SHOPPEAR] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SHOPPEAR] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SHOPPEAR] SET  MULTI_USER 
GO
ALTER DATABASE [SHOPPEAR] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SHOPPEAR] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SHOPPEAR] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SHOPPEAR] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SHOPPEAR] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SHOPPEAR] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SHOPPEAR] SET QUERY_STORE = OFF
GO
USE [SHOPPEAR]
GO
/****** Object:  Table [dbo].[Categoria]    Script Date: 5/19/2024 6:53:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categoria](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 5/19/2024 6:53:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producto](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](max) NULL,
	[descripcion] [varchar](max) NULL,
	[precio] [float] NULL,
	[rating] [varchar](max) NULL,
	[img] [varchar](max) NULL,
	[stock] [int] NULL,
	[idCategoria] [int] NULL,
	[descuento] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 5/19/2024 6:53:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[id] [int] NULL,
	[nombre] [varchar](255) NULL,
	[apellido] [varchar](255) NULL,
	[mail] [varchar](255) NULL,
	[contraseña] [varchar](255) NULL,
	[direccional] [varchar](255) NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Categoria_Producto] FOREIGN KEY([idCategoria])
REFERENCES [dbo].[Categoria] ([id])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Categoria_Producto]
GO
USE [master]
GO
ALTER DATABASE [SHOPPEAR] SET  READ_WRITE 
GO
