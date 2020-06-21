-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 21, 2020 at 04:53 PM
-- Server version: 10.2.31-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u5785535_umkmku`
--

-- --------------------------------------------------------

--
-- Table structure for table `umkm`
--

CREATE TABLE `umkm` (
  `id` int(11) NOT NULL,
  `uid` varchar(255) NOT NULL,
  `nama_umkm` varchar(255) NOT NULL,
  `hp_umkm` varchar(255) NOT NULL,
  `email_umkm` varchar(255) NOT NULL,
  `nama_pemilik` varchar(255) NOT NULL,
  `hp_pemilik` varchar(255) NOT NULL,
  `email_pemilik` varchar(255) NOT NULL,
  `jk_pemilik` enum('Laki - Laki','Perempuan') NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `count_populer` int(11) NOT NULL,
  `foto` varchar(255) NOT NULL DEFAULT 'foto/default.jpg',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `umkm`
--

INSERT INTO `umkm` (`id`, `uid`, `nama_umkm`, `hp_umkm`, `email_umkm`, `nama_pemilik`, `hp_pemilik`, `email_pemilik`, `jk_pemilik`, `longitude`, `latitude`, `count_populer`, `foto`, `created_at`, `updated_at`) VALUES
(25, 'zrr93c5dy2a1ummdntut', 'Data', '02484561', 'renmark.property@gmail.com', 'Andy Setiawan', '0888546', 'andy@renmark.co.id', 'Perempuan', 110.42237150, -6.98628060, 5, 'foto/default.jpg', '2020-06-08 14:02:30', '2020-06-08 14:54:04'),
(24, 'hjkpvwyu1cj5z1tk2xps', 'Renmark Building ', '0248456', 'renmark.property@gmail.com', 'Andy Setiawan', '0888546', 'andy@renmark.co.id', 'Perempuan', 110.42237160, -6.98628060, 2, 'foto/default.jpg', '2020-06-08 14:02:20', '2020-06-08 14:52:36'),
(23, 'tm3jcu0z7ag6f2v7vyxi', 'oke', '06', 'owndigital.dev@gmail.com', 'saya', '0895', 'owndigital.dev@gmail.com', 'Laki - Laki', 110.43315710, -7.03544190, 3, 'foto/default.jpg', '2020-06-07 15:15:35', '2020-06-08 14:52:08'),
(27, 'zfzferaxhppyr28z4vc4', 'Data', '02484561', 'renmark.property@gmail.com', 'Andy Setiawan', '0888546', 'andy@renmark.co.id', 'Perempuan', 110.42237150, -6.98628060, 2, 'foto/default.jpg', '2020-06-08 14:27:16', '2020-06-08 14:28:04');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `uid` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `hp` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL DEFAULT 'http://nardoogroup.com/umkmku/user/foto/default.png',
  `password` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `uid`, `nama`, `email`, `hp`, `foto`, `password`, `created_at`, `updated_at`) VALUES
(2, '72ihmbmtx9hqmuv41nf5', 'Rahmat Trinanda', 'rahmat3nanda@gmail.com', '0895326902971', 'http://nardoogroup.com/umkmku/user/foto/default.png', '5f4dcc3b5aa765d61d8327deb882cf99', '2020-05-17 13:40:48', '2020-05-17 13:40:48'),
(3, 'wwf53826k5gyeaub5gpt', 'Own Digital', '089172689', 'owndigital.dev@gmail.com', 'http://nardoogroup.com/umkmku/user/foto/default.png', '5f4dcc3b5aa765d61d8327deb882cf99', '2020-05-17 17:54:26', '2020-05-17 17:54:26'),
(4, 'p30r3u005vide13hyw0r', 'TOLONG BAPA', 'tanya bapa', 'botax.sehat@gmail.com', 'http://nardoogroup.com/umkmku/user/foto/default.png', '9bed676c7aac7769dcd27053b8596a29', '2020-05-28 18:47:16', '2020-05-28 18:47:16'),
(5, 'bri4tqhyvy00phf8q8y5', 'zidan', '081212719350', 'semeru@gmaol.com', 'http://nardoogroup.com/umkmku/user/foto/default.png', '7c50a2551b0ed04ca7a62cd6a0b6ddc1', '2020-06-04 11:22:02', '2020-06-04 11:22:02'),
(6, '43qnjazn9jngajdnnttm', 'zidan', '081212719350', 'zidan@gmail.com', 'http://nardoogroup.com/umkmku/user/foto/default.png', '7c50a2551b0ed04ca7a62cd6a0b6ddc1', '2020-06-04 11:23:04', '2020-06-04 11:23:04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `umkm`
--
ALTER TABLE `umkm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `umkm`
--
ALTER TABLE `umkm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
