package org.youngsoft.bench.core.services;

import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.msm.api.LiveAction;
import com.day.cq.wcm.msm.api.LiveRelationship;
import com.day.cq.wcm.msm.api.RolloutManager;
import com.day.cq.wcm.msm.commons.BaseAction;
import com.day.cq.wcm.msm.commons.BaseActionFactory;

@Component(metatype=false)
@Service
public class SampleRolloutConfigService extends BaseActionFactory<BaseAction>{

	private static final String LIVE_ACTION_NAME[] = {SampleRolloutConfigService.class.getSimpleName(),"sampleRolloutConfig"};
	
	@Reference
	private RolloutManager rolloutManager;
	
	@Override
	public String createsAction() {
		// TODO Auto-generated method stub
		return LIVE_ACTION_NAME[0];
	}

	@Override
	protected BaseAction newActionInstance(ValueMap arg0) throws WCMException {
		// TODO Auto-generated method stub
		return new SampleAction(arg0, null);
	}

	private static final class SampleAction extends BaseAction {

		protected SampleAction(ValueMap config, BaseActionFactory<? extends LiveAction> liveActionFactory) {
			super(config, liveActionFactory);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void doExecute(Resource arg0, Resource arg1, LiveRelationship arg2, boolean arg3)
				throws RepositoryException, WCMException {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected boolean handles(Resource arg0, Resource arg1, LiveRelationship arg2, boolean arg3)
				throws RepositoryException, WCMException {
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}
}
