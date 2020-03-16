package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.entity.Module;

public class ModuleCreateConverter implements IConverter<Module, ModuleDtoCreate> {

	@Override
	public Module dtoToEntite(ModuleDtoCreate dto) {
		Module entite = new Module(dto.getId(), dto.getNom(),dto.getMatiere());
		return entite;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDtoCreate> dtoliste) {
		List<Module> liste = new ArrayList<Module>();
		for (ModuleDtoCreate dto : dtoliste) {
			liste.add(new Module(dto.getId(), dto.getNom(),dto.getMatiere()));
		}
		return liste;
	}

	@Override
	public ModuleDtoCreate entiteToDto(Module entite) {
		ModuleDtoCreate dto = new ModuleDtoCreate(entite.getId(),entite.getNom(), entite.getMatiere());
		return dto;
	}

	@Override
	public List<ModuleDtoCreate> listEntiteToDto(List<Module> entite) {
		List<ModuleDtoCreate> liste = new ArrayList<ModuleDtoCreate>();
		for (Module e : entite) {
			liste.add(new ModuleDtoCreate(e.getId(),e.getNom(), e.getMatiere()));
		}
		return liste;
	}
	

}
