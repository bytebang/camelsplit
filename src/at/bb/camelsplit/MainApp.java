package at.bb.camelsplit;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import at.bb.camelsplit.businessobjects.BuyItem;
import at.bb.camelsplit.businessobjects.MakeItem;
import at.bb.camelsplit.businessobjects.Order;
import at.bb.camelsplit.routes.ExtPurchaseRoute;
import at.bb.camelsplit.routes.FactoryRoute;
import at.bb.camelsplit.routes.WebshopRoute;

/**
 * This is the mainclass of the project
 * @author gue
 *
 */
public class MainApp 
{
	public static void main(String[] args) throws Exception 
	{
		// Create the camel context and announce the routes
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new ExtPurchaseRoute());
		context.addRoutes(new FactoryRoute());
		context.addRoutes(new WebshopRoute());
		context.start();

		// Now build a Businessobject which shhould be processed
		Order pump_assembly = new Order()
					.addItem(new BuyItem("GearMotor", "An electtromotor", 45))
					.addItem(new MakeItem("PumpHousing-UpperPart", "Moulded steel housing", 4))
					.addItem(new MakeItem("Shaft", "40 mm steel shaft", 1))
					.addItem(new BuyItem("RubberFeet", "Rubber feet to damp vibrations", 0.1))
					.addItem(new BuyItem("RubberFeet", "Rubber feet to damp vibrations", 0.1))
					.addItem(new BuyItem("Bearing", "Ring bearing", 0.75))
					.addItem(new MakeItem("PumpHousing-LowerPart", "Moulded steel housing", 2));
		
		// OK, and now hand the order over to camel
		ProducerTemplate template = context.createProducerTemplate();
		template.sendBody("direct:webshop", pump_assembly);

		// And shutdown camel
		context.getShutdownStrategy().setLogInflightExchangesOnTimeout(true);

	}
}
