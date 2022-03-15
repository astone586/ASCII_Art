
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Graphics {
	
	private static String imgPath = "C:\\Users\\Alston\\eclipse-workspace2\\ASCIIArt\\src\\pawnchamp.png";
	
	public static void main(String[] args) throws IOException {
		  // assume that one character occupies an area of 21×8 pixels
		  char[][] chars = readImage(imgPath, 3, 3);
		  writeToFile("C:\\temp\\image.txt", chars);
		}
	
	
	
	
	
	
	
	

		static char[][] readImage(String path, int scH, int scW) throws IOException {
		  BufferedImage image = ImageIO.read(new File(path));
		  int height = image.getHeight() / scH;
		  int width = image.getWidth() / scW;
		  char[][] chars = new char[height][width];
		  for (int i = 0; i < height; i++) {
		    for (int j = 0; j < width; j++) {
		      // scaling image and accumulating colors
		      int colorRGB = 0;
		      for (int k = 0; k < scH; k++)
		        for (int p = 0; p < scW; p++)
		          colorRGB += image.getRGB(j * scW + p, i * scH + k);
		      // get the average color
		      Color color = new Color(colorRGB / (scH * scW));
		      // read the R, G, B values of the color and get the average brightness
		      int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
		      // get a character depending on the brightness value
		      chars[i][j] = getDensity(brightness);
		    }
		  }
		  return chars;
		}

		// char lists
		
		
		// light to dark
		static final String light = "@QB#NgWM8RDHdOKq9$6khEPXwmeZaoS2yjufF]}{tx1zv7lciL/\\|?*>r^;:_\"~,'.-`";

		//dark to light
		static final String dark = "`-.',~\"\\_:;^r>*?|\\\\/Licl7vz1xt{}]Ffujy2SoaZemwXPEhk6$9qKOdHDR8MWgN#BQ@";


		static final String other = "¶@ØÆMåBNÊßÔR#8Q&mÃ0À$GXZA5ñk2S%±3Fz¢yÝCJf1t7ªLc¿+?(r/¤²!*;\"^:,'.` ";
		
		static final String box = "╬╠╫╋║╉╩┣╦╂╳╇╈┠╚┃╃┻╅┳┡┢┹╀╧┱╙┗┞┇┸┋┯┰┖╲╱┎╘━┭┕┍┅╾│┬┉╰╭╸└┆╺┊─╌┄┈╴╶";
		
		static final String block = "█▉▇▓▊▆▅▌▚▞▀▒▐▍▃▖▂░▁▏";
		
		static final String shapes = "◙◘■▩●▦▣◚◛◕▨▧◉▤◐◒▮◍◑▼▪◤▬◗◭◖◈◎◮◊◫▰◄◯□▯▷▫▽◹△◁▸▭◅▵◌▱▹▿◠◃◦◟◞◜";
		
		
		
		
		
		
		// char list being used
		static final String DENSITY = dark;
		
		
		static char getDensity(int value) {
		  // Since we don't have 255 characters, we have to use percentages
		  int charValue = (int) Math.round(DENSITY.length() / 255.0 * value);
		  charValue = Math.max(charValue, 0);
		  charValue = Math.min(charValue, DENSITY.length() - 1);
		  return DENSITY.charAt(charValue);
		}

		static void writeToFile(String path, char[][] chars) throws IOException {
		  FileWriter writer = new FileWriter(path);
		  for (char[] row : chars) {
		    String str = String.valueOf(row);
		    writer.append(str).write("\n");
		    System.out.println(str);
		  }
		  writer.flush();
		  writer.close();
		}
}
