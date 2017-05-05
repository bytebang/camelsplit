package at.bb.camelsplit.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * This Class contains routes which belong to the factry where parts are built.
 * The direct:factory route is called from the webshop wit parts which
 * are make-items
 * 
 * @author gue
 *
 */
public class FactoryRoute extends RouteBuilder{
	
	@Override
	public void configure() throws Exception 
	{
		from("direct:factory").routeId("factoryroute").description("Simulates a factory where something is built")
		.process(new Processor()
		{
			@Override
			public void process(Exchange exchange) throws Exception 
			{
				System.out.println("The factory is producing :" + exchange.getIn().getBody().toString());
			}
		})
		.end();
	}
}
