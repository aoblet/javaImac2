package fr.upem.library;

public enum Version {
	Numeric("Numeric edition", -30), Physic("Physic edition", 0), Mixt("Mixt edition", 15);
	
	protected String m_suffix;
	protected double m_modifyPricePercentage;
	
	Version(String suffix, double modifyPricePercentage){
		m_suffix = suffix;
		m_modifyPricePercentage = modifyPricePercentage;
	}
	
	public String getSuffix(){
		return m_suffix;
	}
	
	public double modifyPrice(double price){
		return price * (1+(m_modifyPricePercentage/100));
	}
}
