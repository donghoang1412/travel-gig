package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.HelpSupport;
import com.synergisticit.repository.HelpSupportRepository;
@Service
public class HelpSupportServiceImpl implements HelpSupportService {
	@Autowired
	HelpSupportRepository helpSupportRepository;
	@Override
	public HelpSupport save(HelpSupport helpSupport) {
		return helpSupportRepository.save(helpSupport);
	}

	@Override
	public List<HelpSupport> getAllHelpSupport() {
		return helpSupportRepository.findAll();
	}

	@Override
	public void delete(long id) {
		helpSupportRepository.deleteById(id);;
	}

}
