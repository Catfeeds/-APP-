package com.hcb.xigou.util;
/*package com.hcb.apparel.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcb.apparel.service.IPurchaseCarService;
import com.hcb.apparel.service.ISKUService;

public class CarAddThread  {

	@Autowired
	IPurchaseCarService purchaseCarService;
	@Autowired
	ISKUService skuService;;
	
	
	
	
	
	public boolean add(int number, int totalCount) {

		boolean flag = true;

		synchronized (CarAddThread.class) {

			if (totalCount >= number) {
				// 库存足够
           
			} else {
				// 库存不足
				flag = false;

			}
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return flag;
	}

}
*/