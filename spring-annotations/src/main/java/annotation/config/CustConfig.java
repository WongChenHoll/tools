package annotation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * @author WangChenHol
 * @date 2021-9-22 11:11
 **/
//@Component
@SpringBootConfiguration
public class CustConfig {

    @Value("${jason.test.cust-name}")
    public String custName;

    @Value("${jason.test.cust-email}")
    public String custEmail;

    @Value("${jason.test.amount}")
    public BigDecimal amount;

    @Override
    public String toString() {
        return "CustConfig{" +
                "custName='" + custName + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", amount=" + amount +
                '}';
    }
}
