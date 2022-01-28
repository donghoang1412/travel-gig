package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.HelpSupport;

public interface HelpSupportService {
	public HelpSupport save (HelpSupport helpSupport);
	public List<HelpSupport> getAllHelpSupport();
	public void delete (long id);
}
