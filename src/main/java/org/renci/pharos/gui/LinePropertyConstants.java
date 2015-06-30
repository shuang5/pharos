package org.renci.pharos.gui;

public final class LinePropertyConstants {
	//public enum Label {Can,Is}
	//public enum Capabilities {L2Switching,VLANtranslation,IProuting}
	public class Label{
		public static final String can = "Can be connected to";
		public static final String is = "Is connected to";
	}
	public class Capabilities{
		public static final String L2 = "L2Switching";
		public static final String Vlan = "VLANtranslation";
		public static final String IP = "IProuting";
	}
	public class Types{
		public static final String L1="Optical";
		public static final String L2="MPLS";
		public static final String L3="IP";
		public static final String Unkonwn="N/A";
	}
}
