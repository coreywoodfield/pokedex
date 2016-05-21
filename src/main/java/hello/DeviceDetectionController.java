package hello; /**
 * Created by cwoodfie on 3/17/16.
 */

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceDetectionController {

	@RequestMapping(value = "/detect-device", method = RequestMethod.GET)
	public @ResponseBody String detectDevice(Device device) {
		String deviceType = "unknown";
		if (device.isNormal()) {
			deviceType = "computer";
		} else if (device.isMobile()) {
			deviceType = "mobile device";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		}
		String s = device.getDevicePlatform().toString();
		return "You are using a " + deviceType + "!\n" + s;
	}
}
