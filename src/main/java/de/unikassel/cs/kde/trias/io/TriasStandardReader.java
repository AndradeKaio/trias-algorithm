/**
 *  
 *  Trias Algorithm - Trias is an algorithm for computing triadic concepts which
 * 		fulfill minimal support constraints.
 *   
 *  Copyright (C) 2006 - 2009 Knowledge & Data Engineering Group, 
 *                            University of Kassel, Germany
 *                            http://www.kde.cs.uni-kassel.de/
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.unikassel.cs.kde.trias.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import de.unikassel.cs.kde.trias.util.Dimension;

/**
 * @author rja
 *
 */
public class TriasStandardReader implements TriasReader {

	private String delimiter = "\\s";
	private String filePath;
	private int numberOfItems;
	private BufferedReader reader;

	public TriasStandardReader(final BufferedReader reader, int numberOfTriples, final String delimiter) {
		super();
		this.numberOfItems = numberOfTriples;
		this.delimiter = delimiter;
		this.reader = reader;
	}

	public TriasStandardReader(final String path, int numberOfTriples, final String delimiter) {
		super();
		this.numberOfItems = numberOfTriples;
		this.delimiter = delimiter;
		this.filePath = path;
	}

	public int[][] getItemlist() throws NumberFormatException, IOException {

		int[][] utrListe = new int[numberOfItems][Dimension.noOfDimensions + 1];
		int ctr = 0;

		if (reader != null) {
			while (reader.ready()) {
				System.out.println("123");
				System.out.println(reader.readLine());
				String[] parts = reader.readLine().split(delimiter);
				for (int dim = 0; dim < Dimension.noOfDimensions; dim++) {
					utrListe[ctr][dim] = Integer.parseInt(parts[dim]);
					System.out.println(parts[dim]);
				}
				ctr++;
			}
			reader.close();
		} else if (filePath != null){
			Scanner sc;
			try {
				sc = new Scanner(new File(filePath));
				while (sc.hasNextLine()) {
					String[] parts = sc.nextLine().split(" ");
					for (int dim = 0; dim < Dimension.noOfDimensions; dim++) {
						utrListe[ctr][dim] = Integer.parseInt(parts[dim]);
						System.out.println(parts[dim]);
					}
					ctr++;
				}

				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		return utrListe;
	}

}
