package at.bb.camelsplit.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

import at.bb.camelsplit.businessobjects.Item;
import at.bb.camelsplit.businessobjects.Order;

/**
 * This is the Webshop route. The entry point (direct:webshop) accepts
 * a whole order. The order is afterwards splitted up into multiple
 * other exchanges which are sent down the correct routes.
 * 
 * @author gue
 *
 */
public class WebshopRoute extends RouteBuilder 
{
	@Override
	public void configure() throws Exception 
	{
		
		// Step 1: Split the exchange with one order up into multiple exchanges with the items as body
		from("direct:webshop").routeId("webshoproute").description("Shows how to split up an exchange")
		.process(new Processor()
			{
				@Override
				public void process(Exchange exchange) throws Exception 
				{
					// We need a producer 
					ProducerTemplate producer = exchange.getContext().createProducerTemplate();
					Order o = exchange.getIn().getBody(Order.class);
	
					// For each item call the producer and hand over the item and some header information
					// which is afterwards used to route the messages
					for (Item item : o.getItems()) 
					{
						producer.sendBodyAndHeader("direct:itemprocessor", item, "ItemType", item.getClass().getSimpleName());
					}			
				}
			})
		.end();
		
		
		// Step2: Process the items. This function is called from the (direct:webshop) route
		// It decides according to the ItemType header field to which route it should send the item-exchange.
		from("direct:itemprocessor").routeId("itemprocessor").description("Processes the Items")
		.choice().when(header("ItemType").isEqualToIgnoreCase("MakeItem")) 	// Check if the Item is a makeitem
			.to("direct:factory")											// Then build it
		.when(header("ItemType").isEqualToIgnoreCase("BuyItem"))			// Check if the Item is a BuyItem
			.to("direct:purchase")											// Then purchase it
		.otherwise()														// otherwise
			.log("~~~~~~~ DOING NOTHING WITH ${body} ~~~~~~~")				// do nothing
		.end();	
	}
}