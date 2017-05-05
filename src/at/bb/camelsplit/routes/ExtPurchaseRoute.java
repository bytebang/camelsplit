package at.bb.camelsplit.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * This Class contains routes which belong to the Purchase department
 * The direct:purchase route is called from the webshop wit parts which
 * are buy-items
 * 
 * @author gue
 *
 */
public class ExtPurchaseRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception 
	{
		from("direct:purchase").routeId("purchaseroute").description("Simulates a purchase from an external vendor")
		.process(new Processor()
		{
			@Override
			public void process(Exchange exchange) throws Exception 
			{
				System.out.println("Buyer purchases the following item :" + exchange.getIn().getBody().toString());
			}}
		).end();
	}
}