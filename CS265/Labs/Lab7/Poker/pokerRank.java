/**
 * pokerRank.java - Rank (face value) of the cards
 * Demonstrates:	representing typesafe enums w/classes
 *		We get very smart enums, that can write themselves out, and compare
 *		themselves to one another
 *
 * @author:  Kurt Schmidt
 * 3/13
 *
 * java version "1.7.0_17"
 * Java HotSpot(TM) 64-Bit Server VM (build 23.7-b01, mixed mode)
 * on Linux 3.2.0-38-generic #61-Ubuntu SMP  x86_64 x86_64 x86_64 GNU/Linux
 *
 * Note:  This is thanks to:
 *	http://developer.java.sun.com/developer/Books/shiftintojava/page1.html
 *
 */

//package edu.drexel.cs.kschmidt.CS265.cards;

public class pokerRank implements Comparable 
{
		/** 
		 * The name of the card ("Ace", "2', etc.)
		 */
	private final String _name;

		/**
		 * The relative rank of the face value.
		 */
	private final Integer _rank;

		/**
		 * c'tor that accepts a ranking.  Private.  Called to create each static
		 * attribute.
		 *
		 * @param String The name of the suit
		 * @param Integer rank
		 */
	private pokerRank( String name, Integer rank )
	{ _name = name; _rank = rank; }

		/**
		 * Returns the name of the faceValue
		 *
		 * @return String
		 */
	public String toString()  { return _name; }

		/**
		 * Uses rank of card value
		 * @param rhs object to be compared to
		 * @return int this.rank.compareTo( rhs.rank )
		 */
	public int compareTo( Object rhs )
	{
		return this._rank.compareTo( ((pokerRank)rhs).rank() );
	}

		/**
		 * Returns rank so that others can compare
		 * @return _rank
		 */
	public Integer rank() { return _rank; }


		// The actual 13 objects
	public static final pokerRank TWO =
		new pokerRank( "2", new Integer(2) );
	public static final pokerRank THREE =
		new pokerRank( "3", new Integer(3) );
	public static final pokerRank FOUR =
		new pokerRank( "4", new Integer(4) );
	public static final pokerRank FIVE =
		new pokerRank( "5", new Integer(5) );
	public static final pokerRank SIX =
		new pokerRank( "6", new Integer(6) );
	public static final pokerRank SEVEN =
		new pokerRank( "7", new Integer(7) );
	public static final pokerRank EIGHT =
		new pokerRank( "8", new Integer(8) );
	public static final pokerRank NINE =
		new pokerRank( "9", new Integer(9) );
	public static final pokerRank TEN =
		new pokerRank( "10", new Integer(10) );
	public static final pokerRank JACK =
		new pokerRank( "Jack", new Integer(11) );
	public static final pokerRank QUEEN =
		new pokerRank( "Queen", new Integer(12) );
	public static final pokerRank KING =
		new pokerRank( "King", new Integer(13) );
	public static final pokerRank ACE =
		new pokerRank( "Ace", new Integer(14) );
}

