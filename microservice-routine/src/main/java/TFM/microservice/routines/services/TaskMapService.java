package TFM.microservice.routines.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import TFM.microservice.routines.VO.MissionOptionsVO;
import TFM.microservice.routines.VO.TaskMapVO;
import TFM.microservice.routines.VO.TemplateOutputVO;
import TFM.microservice.routines.VO.TemplateVO;
import TFM.microservice.routines.repositories.MissionOptionsRepository;
import TFM.microservice.routines.repositories.TaskMapRepository;
import TFM.microservice.routines.repositories.TemplateRepository;

@Component
public class TaskMapService {
	
	@Autowired
	private TaskMapRepository repository;
	
	@Autowired
	private TemplateRepository repository_template;
	
	@Autowired
	private MissionOptionsRepository repository_options;
	
	public List<TaskMapVO> getTaskMaps(){
		return repository.findAll();
	}
	
	public List<TemplateOutputVO> getTemplates(String taskMap){
		TaskMapVO task = repository.findOneByName(taskMap);
		List<TemplateOutputVO> out = new ArrayList<TemplateOutputVO>();
		List<String> templates = task.getTemplate_options();
		for (String s : templates) {
			TemplateOutputVO temp_out = new TemplateOutputVO(s, new HashMap<String, List<String>>());
			TemplateVO template = repository_template.findOneByName(s);
			if (template != null) { 
				for (String field : template.getFields()) {
					MissionOptionsVO option = repository_options.findAllByTemplateNameAndFieldName(s, field);
					temp_out.addFields(field, option.getOptions());
				}
			}
			out.add(temp_out);
		}
	
		return out;
	}
}
