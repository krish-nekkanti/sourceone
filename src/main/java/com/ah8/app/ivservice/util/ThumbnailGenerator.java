package com.ah8.app.ivservice.util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Class used to generate Thumbnail Images to the Original Image
 * @author AH8
 *
 */
public class ThumbnailGenerator {
	
	public ThumbnailGenerator()	{
	
	}
	/**
	 * Method to generate Thumbnail Image 		
	 * @param originalFile
	 * @param thumbnailFile
	 * @param thumbWidth
	 * @param thumbHeight
	 * @throws Exception
	 */
	public void transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight) throws Exception 	{
		
		try {
			if(originalFile==null || thumbnailFile==null){
				throw new Exception("Original and Thumbnail  File Names should not be null");
			}
			if(originalFile.equalsIgnoreCase(thumbnailFile)){
				throw new Exception("Original and Thumbnail File Names should not be same");
			}
			Image image = javax.imageio.ImageIO.read(new File(originalFile));

			double thumbRatio = (double)thumbWidth / (double)thumbHeight;
			int imageWidth    = image.getWidth(null);
			int imageHeight   = image.getHeight(null);
			double imageRatio = (double)imageWidth / (double)imageHeight;
			if (thumbRatio < imageRatio) {
				thumbHeight = (int)(thumbWidth / imageRatio);
			} else	{
				thumbWidth = (int)(thumbHeight * imageRatio);
			}

			if(imageWidth < thumbWidth && imageHeight < thumbHeight){
				thumbWidth = imageWidth;
				thumbHeight = imageHeight;
			}
			else if(imageWidth < thumbWidth){
				thumbWidth = imageWidth;
			}else if(imageHeight < thumbHeight){
				thumbHeight = imageHeight;
			}
			
			BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.setPaint(Color.WHITE); 
			graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

			javax.imageio.ImageIO.write(thumbImage, "JPG", new File(thumbnailFile));
		} catch (Exception e) {
			throw new Exception("Error while generating Thumbnail Images ");
		}
	}
	/*public static void main(String[] args) throws Exception {
		ThumbnailGenerator tg=new ThumbnailGenerator();
		tg.transform("D:\\Documents\\Original.jpg", "D:\\Documents\\thumb_Original.jpg", 200, 200);
	}*/
}