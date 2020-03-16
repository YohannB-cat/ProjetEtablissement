package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.entity.Module;

public class ModuleConverter implements IConverter<Module, ModuleDto> {

	@Override
	public Module dtoToEntite(ModuleDto dto) {
		Module entite = new Module(0, dto.getNom(),dto.getMatiere());
		return entite;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDto> dtoliste) {
		List<Module> liste = new ArrayList<Module>();
		for (ModuleDto dto : dtoliste) {
			liste.add(new Module(0, dto.getNom(),dto.getMatiere()));
		}
		return liste;
	}

	@Override
	public ModuleDto entiteToDto(Module entite) {
		ModuleDto dto = new ModuleDto(entite.getNom(), entite.getMatiere());
		return dto;
	}

	@Override
	public List<ModuleDto> listEntiteToDto(List<Module> entite) {
		List<ModuleDto> liste = new ArrayList<ModuleDto>();
		for (Module e : entite) {
			liste.add(new ModuleDto(e.getNom(), e.getMatiere()));
		}
		return liste;
	}
	

}
