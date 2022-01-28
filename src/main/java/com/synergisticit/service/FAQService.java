package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.FAQ;

public interface FAQService {
	public FAQ save(FAQ faq);
	public List<FAQ> findAllFAQs();
}
