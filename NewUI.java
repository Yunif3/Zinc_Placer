import java.awt.*;
import java.util.*;
import javax.swing.*;
public class NewUI {
		private static float[] atom1 = new float[3];
		private static float[] atom2 = new float[3];
		private static float[] atom3 = new float[3];
		private static float[] secondAtom1 = new float[3];
		private static float[] secondAtom2 = new float[3];
		private static float[] secondAtom3 = new float[3];
		private static float[] thirdAtom1 = new float[3];
		private static float[] thirdAtom2 = new float[3];
		private static float[] thirdAtom3 = new float[3];
		private static float[] fourthAtom1 = new float[3];
		private static float[] fourthAtom2 = new float[3];
		private static float[] fourthAtom3 = new float[3];
		private static float[] histidine1 = new float[3];
		private static float[] histidine2 = new float[3];
		private static float[] histidine3 = new float[3];
		private static float[] histidine4 = new float[3];
		private static float[] histidine5 = new float[3];
		private static float[] histidine6 = new float[3];
		private static float[] histidine7 = new float[3];
		private static int yesNo = 0;
		private static int repeats = 60;
	
		
		public static void main (String [] args) {
			RotatedCircle circle1, circle2, circle3, circle4;
			float[][] circleCoords1 = new float[3][repeats];
			float[][] circleCoords2 = new float[3][repeats];
			float[][] circleCoords3 = new float[3][repeats];
			float[][] circleCoords4 = new float[3][repeats];

			JFrame frame = new JFrame();
			frame.setSize(1000, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			Label a = new Label("Coordinates");
			panel.add(a);
			JTextArea txt = new JTextArea(2,20);
			panel.add(txt);
			Label b = new Label("Distances");
			panel.add(b);
			JTextArea txt2 = new JTextArea(2,20);
			panel.add(txt2);
			frame.add(panel, BorderLayout.NORTH);
			
			Connector oneSolution;
			Histidine myHistidine;
			Scanner scan = new Scanner(System.in);
			
			System.out.println("enter 1 if it's CYS3 - HIS. If it's CYS4, enter 0.");
			yesNo = scan.nextInt();
			// group 1
			System.out.println("FIRST ligand CA"); 
			for (int n = 0; n < 3; n++) 
				atom1[n] = scan.nextFloat();
			System.out.println("FIRST ligand CB");
			for (int n = 0; n < 3; n++) 
				atom2[n] = scan.nextFloat();
			System.out.println("FIRST ligand SG");
			for (int n = 0; n < 3; n++) 
				atom3[n] = scan.nextFloat();
			
			// group 2
			System.out.println("SECOND ligand CA");
			for (int n = 0; n < 3; n++) 
				secondAtom1[n] = scan.nextFloat();
			System.out.println("SECOND ligand CB");
			for (int n = 0; n < 3; n++) 
				secondAtom2[n] = scan.nextFloat();
			System.out.println("SECOND ligand SG");
			for (int n = 0; n < 3; n++) 
				secondAtom3[n] = scan.nextFloat();
			
			// group 3
			System.out.println("THIRD ligand CA");
			for (int n = 0; n < 3; n++) 
				thirdAtom1[n] = scan.nextFloat();
			System.out.println("THIRD ligand CB");
			for (int n = 0; n < 3; n++) 
				thirdAtom2[n] = scan.nextFloat();
			System.out.println("THIRD ligand SG");
			for (int n = 0; n < 3; n++) 
				thirdAtom3[n] = scan.nextFloat();
			
			if (yesNo == 0) {
			// group 4
			System.out.println("FOURTH ligand CA");
			for (int n = 0; n < 3; n++) 
				fourthAtom1[n] = scan.nextFloat();
			System.out.println("FOURTH ligand CB");
			for (int n = 0; n < 3; n++) 
				fourthAtom2[n] = scan.nextFloat();
			System.out.println("FOURTH ligand SG");
			for (int n = 0; n < 3; n++) 
				fourthAtom3[n] = scan.nextFloat();
			}
			
			if (yesNo == 1) {
				System.out.println("histidine CE1");
				for (int n = 0; n < 3; n++) 
					histidine1[n] = scan.nextFloat();
				System.out.println("histidine NE2");
				for (int n = 0; n < 3; n++) 
					histidine2[n] = scan.nextFloat();
				System.out.println("histidine CD2");
				for (int n = 0; n < 3; n++) 
					histidine3[n] = scan.nextFloat();
				System.out.println("histidine ND1");
				for (int n = 0; n < 3; n++) 
					histidine4[n] = scan.nextFloat();
				System.out.println("histidine CG");
				for (int n = 0; n < 3; n++) 
					histidine5[n] = scan.nextFloat();
				System.out.println("histidine CB");
				for (int n = 0; n < 3; n++) 
					histidine6[n] = scan.nextFloat();
				System.out.println("histidine CA");
				for (int n = 0; n < 3; n++) 
					histidine7[n] = scan.nextFloat();
			}
				float [][] tempArray = new float[3][1];
				circle1 = new RotatedCircle(atom1,atom2,atom3); //circles 1&3 calculation
				circle1.unitVector();
				circle1.translation();
				for (int c =0; c < repeats; c++) {
					circle1.changeRad();
					circle1.changeRD();
					tempArray = circle1.multMatrix();
					circleCoords1[0][c] = tempArray[0][0] + atom2[0];
					circleCoords1[1][c] = tempArray[1][0] + atom2[1];
					circleCoords1[2][c] = tempArray[2][0] + atom2[2];
				}
				circle2 = new RotatedCircle(secondAtom1,secondAtom2,secondAtom3);
				circle2.unitVector();
				circle2.translation();
				for (int c =0; c < repeats; c++) {
					circle2.changeRad();
					circle2.changeRD();
					tempArray = circle2.multMatrix();
					circleCoords2[0][c] = tempArray[0][0] + secondAtom2[0];
					circleCoords2[1][c] = tempArray[1][0] + secondAtom2[1];
					circleCoords2[2][c] = tempArray[2][0] + secondAtom2[2];
				}
				
				circle3 = new RotatedCircle(thirdAtom1,thirdAtom2,thirdAtom3); //circles 1&3 calculation
				circle3.unitVector();
				circle3.translation();
				for (int c =0; c < repeats; c++) {
					circle3.changeRad();
					circle3.changeRD();
					tempArray = circle3.multMatrix();
					circleCoords3[0][c] = tempArray[0][0] + thirdAtom2[0];
					circleCoords3[1][c] = tempArray[1][0] + thirdAtom2[1];
					circleCoords3[2][c] = tempArray[2][0] + thirdAtom2[2];
				}
				
				if (yesNo == 0) {
				circle4 = new RotatedCircle(fourthAtom1,fourthAtom2,fourthAtom3);
				circle4.unitVector();
				circle4.translation();
				for (int c =0; c < repeats; c++) {
					circle4.changeRad();
					circle4.changeRD();
					tempArray = circle4.multMatrix();
					circleCoords4[0][c] = tempArray[0][0] + fourthAtom2[0];
					circleCoords4[1][c] = tempArray[1][0] + fourthAtom2[1];
					circleCoords4[2][c] = tempArray[2][0] + fourthAtom2[2];
				}
				oneSolution = new Connector(circleCoords1, circleCoords2, circleCoords3, circleCoords4, atom2, secondAtom2, thirdAtom2, fourthAtom2);
				oneSolution.Jdisplay(txt);
				}
				
				if (yesNo == 1) {
					myHistidine = new Histidine(histidine1, histidine2, histidine3, histidine4, histidine5, histidine6, histidine7, circleCoords1, circleCoords2, circleCoords3, atom2, secondAtom2, thirdAtom2);
					myHistidine.display(txt);
				}
				
				frame.setVisible(true);
			}
		
			
	}
