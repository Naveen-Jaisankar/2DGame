package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile; 
	public int mapTileNum [][][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[500];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/home.txt",0);
		loadMap("/maps/homeInterior.txt",1);
		loadMap("/maps/playground.txt",2);
		loadMap("/maps/school_inner.txt",3);
		loadMap("/maps/college.txt",4);
		loadMap("/maps/school_inner.txt",5);
		loadMap("/maps/market.txt",6);
	}
	
	public void getTileImage() {
		// 0-9 are placeholder images, because single digit number doesn't look good on
		// worldmap text file.
		setup(0, "grass00", false);
		setup(1, "grass01", false);
		setup(2, "grass02", false);
		setup(3, "redcar1", false);
		setup(4, "redcar2", false);
		setup(5, "redcar3", false);
		setup(6, "redcar4", false);
		setup(7, "redcar5", false);
		setup(8, "redcar6", false);
		setup(9, "bus", false);
		setup(10, "grass00", false);
		setup(11, "grass00", false);
		setup(12, "water00", true);
		setup(13, "water01", true);
		setup(14, "water02", true);
		setup(15, "water03", true);
		setup(16, "water04", true);
		setup(17, "water05", true);
		setup(18, "water06", true);
		setup(19, "water07", true);
		setup(20, "water08", true);
		setup(21, "water09", true);
		setup(22, "water10", true);
		setup(23, "water11", true);
		setup(24, "water12", true);
		setup(25, "water13", true);
		setup(26, "road00", false);
		setup(27, "road01", false);
		setup(28, "road02", false);
		setup(29, "road03", false);
		setup(30, "road04", false);
		setup(31, "road05", false);
		setup(32, "road06", false);
		setup(33, "road07", false);
		setup(34, "road08", false);
		setup(35, "road09", false);
		setup(36, "road10", false);
		setup(37, "road11", false);
		setup(38, "road12", false);
		setup(39, "earth", false);
		setup(40, "wall", true);
		setup(41, "tree", true);
		setup(42, "Platform1", true);
		setup(43, "Platform2", true);
		setup(44, "Platform3", true);
		setup(45, "Platform4", true);
		setup(46, "Platform5", true);
		setup(47, "Platform6", true);
		setup(48, "Platform7", true);
		setup(49, "Platform8", true);
		setup(50, "Platform9", true);
		setup(51, "Platform10", true);
		setup(52, "Platform11", true);
		setup(53, "Platform12", true);
		setup(54, "Platform13", true);
		setup(55, "Platform14", true);
		setup(56, "Platform15", true);
		setup(57, "road13", false);
		setup(58, "road14", false);
		setup(59, "road15", false);
		setup(60, "top1", true);
		setup(61, "top2", true);
		setup(62, "top3", true);
		setup(63, "top4", true);
		setup(64, "top5", true);
		setup(65, "top6", true);
		setup(66, "top7", true);
		setup(67, "top8", true);
		setup(68, "top9", true);
		setup(69, "top10", true);
		setup(70, "top11", true);
		setup(71, "top12", true);
		setup(72, "home1", true);
		setup(73, "home2", true);
		setup(74, "home3", true);
		setup(75, "home4", true);
		setup(76, "home5", true);
		setup(77, "home6", true);
		setup(78, "home7", true);
		setup(79, "home8", true);
		setup(80, "home9", true);
		setup(81, "pump", true);
		setup(82, "window1", true);
		setup(83, "window2", true);
		setup(84, "window3", true);
		setup(85, "steel1", true);
		setup(86, "steel2", true);
		setup(87, "door1", false);
		setup(88, "door2", false);
		setup(89, "door3", true);
		setup(90, "road16", false);
		setup(91, "road17", false);
		setup(92, "road18", false);
		setup(93, "road19", false);
		setup(94, "road20", false);
		setup(95, "Platform16", false);
		setup(96, "Platform17", false);
		setup(97, "grey1", true);
		setup(98, "grey2", true);
		setup(99, "grey3", true);
		setup(100, "grey4", true);
		setup(101, "grey5", true);
		setup(102, "grey6", true);
		setup(103, "grey7", true);
		setup(104, "grey8", true);
		setup(105, "grey9", true);
		setup(106, "grey10", true);
		setup(107, "grey11", true);
		setup(108, "grey12", true);
		setup(109, "steel3", true);
		setup(110, "flat1", true);
		setup(111, "flat2", true);
		setup(112, "flat3", true);
		setup(113, "flat4", true);
		setup(114, "flat5", true);
		setup(115, "flat6", true);
		setup(116, "flat7", true);
		setup(117, "flat8", true);
		setup(118, "flat9", true);
		setup(119, "window4", true);
		setup(120, "window5", true);
		setup(121, "window6", true);
		setup(122, "door4", false);
		setup(123, "door5", false);
		setup(124, "steel4", true);
		setup(125, "busstop", true);
		setup(126, "bin", true);
		setup(127, "box1", true);
		setup(128, "box2", true);
		setup(129, "brick1", true);
		setup(130, "brick2", true);
		setup(131, "brick3", true);
		setup(132, "brick4", true);
		setup(133, "brick5", true);
		setup(134, "brick6", true);
		setup(135, "brick7", true);
		setup(136, "brick8", true);
		setup(137, "brick9", true);
		setup(138, "Lgrey1", true);
		setup(139, "Lgrey2", true);
		setup(140, "Lgrey3", true);
		setup(141, "Lgrey4", true);
		setup(142, "Lgrey5", true);
		setup(143, "Lgrey6", true);
		setup(144, "Lgrey7", true);
		setup(145, "Lgrey8", true);
		setup(146, "Lgrey9", true);
		setup(147, "Lgrey10", true);
		setup(148, "Lgrey11", true);
		setup(149, "Lgrey12", true);
		setup(150, "ytop1", true);
		setup(151, "ytop2", true);
		setup(152, "ytop3", true);
		setup(153, "ytop4", true);
		setup(154, "ytop5", true);
		setup(155, "ytop6", true);
		setup(156, "ytop7", true);
		setup(157, "ytop8", true);
		setup(158, "ytop9", true);
		setup(159, "ytop10", true);
		setup(160, "ytop11", true);
		setup(161, "ytop12", true);
		setup(162, "pool1", true);
		setup(163, "pool2", true);
		setup(164, "pool3", true);
		setup(165, "pool4", true);
		setup(166, "pool5", true);
		setup(167, "pool6", true);
		setup(168, "pool7", true);
		setup(169, "pool8", true);
		setup(170, "pool9", true);
		setup(171, "park1", true);
		setup(172, "park2", true);
		setup(173, "park3", true);
		setup(174, "park4", true);
		setup(175, "park5", true);
		setup(176, "park6", true);
		setup(177, "park7", true);
		setup(178, "park8", true);
		setup(179, "park9", true);
		setup(180, "mirror1", true);
		setup(181, "mirror2", true);
		setup(182, "mirror3", true);
		setup(183, "mirror4", true);
		setup(184, "mirror5", true);
		setup(185, "mirror6", true);
		setup(186, "banner1", true);
		setup(187, "banner2", true);
		setup(188, "banner3", true);
		setup(189, "shamiyana1", true);
		setup(190, "shamiyana2", true);
		setup(191, "shamiyana3", true);
		setup(192, "door6", true);
		setup(193, "door7", true);
		setup(194, "door8", true);
		setup(195, "door9", true);
		setup(196, "door10", true);
		setup(197, "door11", true);
		setup(198, "door12", true);
		setup(199, "door13", true);
		setup(200, "otop1", true);
		setup(201, "otop2", true);
		setup(202, "otop3", true);
		setup(203, "otop4", true);
		setup(204, "otop5", true);
		setup(205, "otop6", true);
		setup(206, "otop7", true);
		setup(207, "otop8", true);
		setup(208, "otop9", true);
		setup(209, "otop10", true);
		setup(210, "gtop1", true);
		setup(211, "gtop2", true);
		setup(212, "gtop3", true);
		setup(213, "gtop4", true);
		setup(214, "gtop5", true);
		setup(215, "gtop6", true);
		setup(216, "gtop7", true);
		setup(217, "gtop8", true);
		setup(218, "gtop9", true);
		setup(219, "gtop10", true);
		setup(220, "bar1", true);
		setup(221, "bar2", true);
		setup(222, "bar3", true);
		setup(223, "bar4", true);
		setup(224, "bar5", true);
		setup(225, "bar6", true);
		setup(226, "bar7", true);
		setup(227, "bar8", true);
		setup(228, "bar9", true);
		setup(229, "bar10", true);
		setup(230, "bar11", true);
		setup(231, "bar12", true);
		setup(232, "bar13", true);
		setup(233, "bar14", true);
		setup(234, "bar15", true);
		setup(235, "bar16", true);
		setup(236, "obrick1", true);
		setup(237, "obrick2", true);
		setup(238, "obrick3", true);
		setup(239, "obrick4", true);
		setup(240, "obrick5", true);
		setup(241, "obrick6", true);
		setup(242, "obrick7", true);
		setup(243, "obrick8", true);
		setup(244, "obrick9", true);
		setup(245, "obrick10", true);
		setup(246, "obrick11", true);
		setup(247, "obrick12", true);
		setup(248, "obrick13", true);
		setup(249, "obrick14", true);
		setup(250, "obrick15", true);
		setup(251, "obrick16", true);
		setup(252, "Platform0", true);
		setup(253, "dress1", true);
		setup(254, "dress2", true);
		setup(255, "steps1", true);
		setup(256, "rope", true);
		setup(257, "steel5", true);
		setup(258, "steel6", true);
		setup(259, "window7", true);
		setup(260, "fill1", true);
		setup(261, "fill2", true);
		setup(262, "fill3", true);
		setup(263, "fill4", true);
		setup(264, "fill5", true);
		setup(265, "fill6", true);
		setup(266, "fill7", true);
		setup(267, "fill8", true);
		setup(268, "glass1", true);
		setup(269, "glass2", true);
		setup(270, "glass3", true);
		setup(271, "glass4", true);
		setup(272, "glass5", true);
		setup(273, "glass6", true);
		setup(274, "glass7", true);
		setup(275, "glass8", true);
		setup(276, "glass9", true);
		setup(277, "steel7", true);
		setup(278, "steel8", true);
		setup(279, "house1", true);
		setup(280, "house2", true);
		setup(281, "house3", true);
		setup(282, "house4", true);
		setup(283, "house5", true);
		setup(284, "house6", true);
		setup(285, "house7", true);
		setup(286, "house8", true);
		setup(287, "house9", true);
		setup(288, "house10", true);
		setup(289, "house11", true);
		setup(290, "house12", true);
		setup(291, "house13", true);
		setup(292, "house14", true);
		setup(293, "house15", true);
		setup(294, "house16", true);
		setup(295, "house17", true);
		setup(296, "king1", true);
		setup(297, "king2", true);
		setup(298, "king3", true);
		setup(299, "stall1", true);
		setup(300, "stall2", true);
		setup(301, "stall3", true);
		setup(302, "stall4", true);
		setup(303, "veggie1", true);
		setup(304, "veggie2", true);
		setup(305, "veggie3", true);
		setup(306, "cbrick1", true);
		setup(307, "cbrick2", true);
		setup(308, "castle1", true);
		setup(309, "castle2", true);
		setup(310, "castle3", true);
		setup(311, "castle4", true);
		setup(312, "castle5", true);
		setup(313, "castle6", true);
		setup(314, "castle7", true);
		setup(315, "castle8", true);
		setup(316, "castle9", true);
		setup(317, "cbrick3", true);
		setup(318, "cbrick4", true);
		setup(319, "gate1", true);
		setup(320, "gate2", true);
		setup(321, "gate3", true);
		setup(322, "gate4", true);
		setup(323, "solider", true);
		setup(324, "tower1", true);
		setup(325, "tower2", true);
		setup(326, "ladder", true);
		setup(327, "window8", true);
		setup(328, "door14", false);
		setup(329, "steel9", true);
		setup(330, "shamiyana4", true);
		setup(331, "shamiyana5", true);
		setup(332, "shamiyana6", true);
		setup(333, "shamiyana7", true);
		setup(334, "glass10", true);
		setup(335, "glass11", true);
		setup(336, "glass12", true);
		setup(337, "door16", true);
		setup(338, "door17", true);
		setup(339, "obrick17", true);
		setup(340, "obrick18", true);
		setup(341, "door15", true);
		setup(342, "bin1", true);
		setup(343, "board1", true);
		setup(344, "board2", true);
		setup(345, "board3", true);
		setup(346, "chair", true);
		setup(347, "photo", true);
		setup(348, "photo1", true);
		setup(349, "photo2", true);
		setup(350, "photo3", true);
		this.setup(400, "rhouse1", true);
		this.setup(401, "rhouse2", true);
		this.setup(402, "rhouse3", true);
		this.setup(403, "rhouse4", true);
		this.setup(404, "rhouse5", true);
		this.setup(405, "rhouse6", true);
		this.setup(406, "rhouse7", true);
		this.setup(407, "rhouse8", true);
		this.setup(408, "rhouse9", true);
		this.setup(409, "rhouse10", true);
		this.setup(410, "rhouse11", true);
		this.setup(411, "rhouse12", true);
		this.setup(412, "rwindow", true);
		this.setup(413, "rdoor1", true);
		this.setup(414, "rdoor2", true);
		this.setup(415, "rdoor3", true);
		this.setup(416, "bhouse1", true);
		this.setup(417, "bhouse2", true);
		this.setup(418, "bhouse3", true);
		this.setup(419, "bhouse4", true);
		this.setup(420, "bhouse5", true);
		this.setup(421, "bhouse6", true);
		this.setup(422, "bhouse7", true);
		this.setup(423, "bhouse8", true);
		this.setup(424, "bhouse9", true);
		this.setup(425, "bhouse10", true);
		this.setup(426, "bhouse11", true);
		this.setup(427, "bhouse12", true);
		this.setup(428, "bdoor1", true);
		this.setup(429, "bdoor2", true);
		this.setup(430, "bdoor3", true);
		this.setup(431, "bwindow", true);
		this.setup(432, "path", false);
		this.setup(433, "lawn1", true);
		this.setup(434, "lawn2", true);
		this.setup(435, "lawn3", true);
		this.setup(436, "lawn4", true);
		this.setup(437, "lawn5", true);
		this.setup(438, "lawn6", true);
		this.setup(439, "lawn7", true);
		this.setup(440, "lawn8", true);
		this.setup(441, "honeycomb", true);
		this.setup(442, "border1", true);
		this.setup(443, "borderside", true);
		this.setup(444, "border2", true);
		this.setup(445, "borderfside", true);
		this.setup(446, "border3", true);
		this.setup(447, "border4", true);
		this.setup(448, "floorlcside", false);
		this.setup(449, "floormside", false);
		this.setup(450, "floorrcside", false);
		this.setup(451, "floorlside", false);
		this.setup(452, "floorrside", false);
		this.setup(453, "floormiddle", false);
		this.setup(454, "ywall", true);
		this.setup(455, "keyboard1", true);
		this.setup(456, "keyboard2", true);
		this.setup(457, "mir1", true);
		this.setup(458, "mir2", true);
		this.setup(459, "firelamp1", true);
		this.setup(460, "firelamp2", true);
		this.setup(461, "flower", true);
		this.setup(462, "shield1", true);
		this.setup(463, "shield2", true);
		this.setup(464, "shield3", true);
		this.setup(465, "shield4", true);
		this.setup(466, "dt1", true);
		this.setup(467, "dt2", true);
		this.setup(468, "dt3", true);
		this.setup(469, "woodenchair", true);
		this.setup(470, "owc", true);
		this.setup(471, "teapoy", true);
		this.setup(472, "sofa", true);
		this.setup(473, "sofaside", true);
		this.setup(474, "sofaback1", true);
		this.setup(475, "sofaback2", true);
		this.setup(476, "sofaback3", true);
		this.setup(477, "candle", true);
		this.setup(478, "tab1", true);
		this.setup(479, "tab2", true);
		this.setup(480, "tab3", true);
		this.setup(481, "tab4", true);
		this.setup(482, "tab5", true);
		this.setup(483, "tab6", true);
		this.setup(484, "tab7", true);
		this.setup(484, "post1", true);
		this.setup(485, "post2", true);
		this.setup(486, "post3", true);

	}

	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].colllision = collision;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col=0;
			int row=0;
			
			while(col<gp.maxWorldCol && row <gp.maxWorldRow) {
				String line = br.readLine();
				while(col<gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row]=num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col =0;
					row++;
				}
			}
			br.close();			
		}catch(Exception e){
			
		}
	}
	public void draw(Graphics2D g2) {

		int worldCol =0;
		int worldRow = 0;
		
		
		while(worldCol< gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			int worldX = gp.tileSize * worldCol;
			int worldY = gp.tileSize * worldRow;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				//Here we dont need to scale the image, coz we have implemented a method in UtilityTool - scaleImage, which will scale the image beforehand, thus improving the rendering performance
				//g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,gp.tileSize,null);
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			
			worldCol++;

			
			if(worldCol == gp.maxWorldCol) {
				worldCol =0;
				worldRow++;
			}
		}
	}
}
