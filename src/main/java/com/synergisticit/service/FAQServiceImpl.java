package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.FAQ;
import com.synergisticit.repository.FAQRepository;

@Service
public class FAQServiceImpl implements FAQService {

	@Autowired
	private FAQRepository faqRepository;
	@Override
	public FAQ save(FAQ faq) {
		return faqRepository.save(faq);
	}
	@Override
	public List<FAQ> findAllFAQs() {
		return faqRepository.findAll();
	}

}
