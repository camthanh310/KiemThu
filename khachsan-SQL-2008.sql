USE [KhachSan]
GO
/****** Object:  Table [dbo].[DatPhong]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DatPhong](
	[MaDatPhong] [int] NOT NULL,
	[MaKhachHang] [int] NULL,
	[NgayDatPhong] [date] NULL,
	[NgayTraPhong] [date] NULL,
	[MaPhong] [int] NULL,
 CONSTRAINT [PK_DatPhong] PRIMARY KEY CLUSTERED 
(
	[MaDatPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [int] NOT NULL,
	[MaDatPhong] [int] NULL,
	[NgayThanhToan] [date] NULL,
	[TongTien] [float] NULL,
	[MaNguoiDung] [int] NOT NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [int] NOT NULL,
	[HoTen] [nvarchar](200) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[NgaySinh] [date] NULL,
	[SoDienThoai] [varchar](11) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[MaLoaiPhong] [int] NOT NULL,
	[TenLoaiPhong] [nvarchar](50) NULL,
	[Gia] [float] NULL,
	[SoGiuongNgu] [int] NULL,
 CONSTRAINT [PK_MaLoaiPhong] PRIMARY KEY CLUSTERED 
(
	[MaLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Phong]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[MaPhong] [int] NOT NULL,
	[TenPhong] [nvarchar](50) NULL,
	[MaLoaiPhong] [int] NULL,
	[TinhTrangPhong] [bit] NULL,
 CONSTRAINT [PK_Phong] PRIMARY KEY CLUSTERED 
(
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 3/24/2018 4:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[ID] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[LoaiPhong] ([MaLoaiPhong], [TenLoaiPhong], [Gia], [SoGiuongNgu]) VALUES (1, N'Phòng Loại 1_*', 100000, 1)
INSERT [dbo].[LoaiPhong] ([MaLoaiPhong], [TenLoaiPhong], [Gia], [SoGiuongNgu]) VALUES (2, N'Phòng Loai 1_**', 120000, 2)
INSERT [dbo].[TaiKhoan] ([ID], [name], [username], [password]) VALUES (1, N'A', N'quanly', N'123123')
INSERT [dbo].[TaiKhoan] ([ID], [name], [username], [password]) VALUES (2, N'B', N'nhanvien', N'123123')
ALTER TABLE [dbo].[DatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DatPhong_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[DatPhong] CHECK CONSTRAINT [FK_DatPhong_KhachHang]
GO
ALTER TABLE [dbo].[DatPhong]  WITH CHECK ADD  CONSTRAINT [FK_DatPhong_Phong] FOREIGN KEY([MaPhong])
REFERENCES [dbo].[Phong] ([MaPhong])
GO
ALTER TABLE [dbo].[DatPhong] CHECK CONSTRAINT [FK_DatPhong_Phong]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_DatPhong] FOREIGN KEY([MaDatPhong])
REFERENCES [dbo].[DatPhong] ([MaDatPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_DatPhong]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_TaiKhoan] FOREIGN KEY([MaNguoiDung])
REFERENCES [dbo].[TaiKhoan] ([ID])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_TaiKhoan]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_LoaiPhong] FOREIGN KEY([MaLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([MaLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_LoaiPhong]
GO
