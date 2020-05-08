-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Bulan Mei 2020 pada 04.38
-- Versi server: 10.4.8-MariaDB
-- Versi PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jasa_cetak`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbldatareseller`
--

CREATE TABLE `tbldatareseller` (
  `NoID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `Nama_Lengkap` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Jenis_Kelamin` varchar(15) CHARACTER SET latin1 NOT NULL,
  `Alamat` varchar(50) CHARACTER SET latin1 NOT NULL,
  `NoTelp` varchar(15) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbldatareseller`
--

INSERT INTO `tbldatareseller` (`NoID`, `Nama_Lengkap`, `Jenis_Kelamin`, `Alamat`, `NoTelp`) VALUES
('B0001', 'aku', 'Laki-laki', 'wng', '08111111111');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tblinputbahan`
--

CREATE TABLE `tblinputbahan` (
  `kode_bahan` varchar(10) CHARACTER SET latin1 NOT NULL,
  `jenis_bahan` varchar(20) CHARACTER SET latin1 NOT NULL,
  `gramatur` varchar(10) CHARACTER SET latin1 NOT NULL,
  `ukuran` varchar(10) CHARACTER SET latin1 NOT NULL,
  `harga_satuan` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tblinputbahan`
--

INSERT INTO `tblinputbahan` (`kode_bahan`, `jenis_bahan`, `gramatur`, `ukuran`, `harga_satuan`) VALUES
('A3-AC210', 'Art Cartoon', '210', 'A3', 3000),
('A3-AC230', 'Art Cartoon', '230', 'A3', 3000),
('A3-AC260', 'Art Cartoon', '260', 'A3', 3000),
('A3-AP120', 'Art Paper', '120', 'A3', 2800),
('A3-AP150', 'Art Paper', '150', 'A3', 2800),
('A3-HVS100', 'HVS', '100', 'A3', 2600),
('A3-HVS80', 'HVS', '80', 'A3', 2600);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tblloginadmin`
--

CREATE TABLE `tblloginadmin` (
  `username` varchar(10) CHARACTER SET latin1 NOT NULL,
  `password` varchar(10) CHARACTER SET latin1 NOT NULL,
  `nama_lengkap` varchar(20) CHARACTER SET latin1 NOT NULL,
  `jenis_kelamin` varchar(15) CHARACTER SET latin1 NOT NULL,
  `no.telp` varchar(15) CHARACTER SET latin1 NOT NULL,
  `alamat` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tblloginadmin`
--

INSERT INTO `tblloginadmin` (`username`, `password`, `nama_lengkap`, `jenis_kelamin`, `no.telp`, `alamat`) VALUES
('sibee', '12345bagas', 'Bagas', 'Laki-laki', '082313597752', 'Wonogiri');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbloperator`
--

CREATE TABLE `tbloperator` (
  `username` varchar(10) CHARACTER SET latin1 NOT NULL,
  `password` varchar(10) CHARACTER SET latin1 NOT NULL,
  `nama_lengkap` varchar(20) CHARACTER SET latin1 NOT NULL,
  `jenis_kelamin` varchar(15) CHARACTER SET latin1 NOT NULL,
  `notelp` varchar(15) CHARACTER SET latin1 NOT NULL,
  `alamat` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbloperator`
--

INSERT INTO `tbloperator` (`username`, `password`, `nama_lengkap`, `jenis_kelamin`, `notelp`, `alamat`) VALUES
('aaa', 'aaa', 'aaa', 'Perempuan', '08123456789', 'a');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tblpembelian`
--

CREATE TABLE `tblpembelian` (
  `No_transaksi` varchar(10) CHARACTER SET latin1 NOT NULL,
  `kode_bahan` varchar(10) CHARACTER SET latin1 NOT NULL,
  `harga_satuan` int(10) NOT NULL,
  `jumlah_cetak` int(10) NOT NULL,
  `harga_cetak` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tblpembelian`
--

INSERT INTO `tblpembelian` (`No_transaksi`, `kode_bahan`, `harga_satuan`, `jumlah_cetak`, `harga_cetak`, `bayar`, `kembalian`, `tanggal`) VALUES
('F0001', 'A3-AC210', 3000, 4, 12000, 15000, 3000, '2020-05-04'),
('F0002', 'A3-AC260', 3000, 6, 18000, 40000, 9000, '2020-05-05'),
('F0002', 'A3-HVS80', 2600, 5, 13000, 40000, 9000, '2020-05-05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbltrans`
--

CREATE TABLE `tbltrans` (
  `No` int(10) NOT NULL,
  `Kode_Bahan` varchar(10) CHARACTER SET latin1 NOT NULL,
  `harga_satuan` int(10) NOT NULL,
  `jumlah_cetak` int(10) NOT NULL,
  `harga_cetak` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbldatareseller`
--
ALTER TABLE `tbldatareseller`
  ADD PRIMARY KEY (`NoID`);

--
-- Indeks untuk tabel `tblinputbahan`
--
ALTER TABLE `tblinputbahan`
  ADD PRIMARY KEY (`kode_bahan`);

--
-- Indeks untuk tabel `tblloginadmin`
--
ALTER TABLE `tblloginadmin`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `tbloperator`
--
ALTER TABLE `tbloperator`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `tbltrans`
--
ALTER TABLE `tbltrans`
  ADD PRIMARY KEY (`No`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbltrans`
--
ALTER TABLE `tbltrans`
  MODIFY `No` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
