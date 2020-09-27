package mshaik.meru.online.pricing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("pricing-params")
public class CloudConfig
{
	private String  taxadjpct;
	private String maxtaxpct;

	public String getMaxtaxpct() {
		return maxtaxpct;
	}
	public void setMaxtaxpct(String maxtaxpct) {
		this.maxtaxpct = maxtaxpct;
	}
	
	public String getTaxadjpct() {
		return taxadjpct;
	}
	public void setTaxadjpct(String taxadjpct) {
		this.taxadjpct = taxadjpct;
	}

}