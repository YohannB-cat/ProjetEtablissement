package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ModuleDtoCreate;
import com.fr.adaming.entity.Module;
@Component
public class ModuleCreateConverter implements IConverter<Module, ModuleDtoCreate> {

	@Override
	public Module dtoToEntite(ModuleDtoCreate dto) {
		if(dto==null) {
			return null;
		}
		Module entite = new Module(dto.getId(), dto.getNom(),dto.getMatiere());
		return entite;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDtoCreate> dtoliste) {
		if(dtoliste==null) {
			return null;
		}
		List<Module> liste = new ArrayList<Module>();
		for (ModuleDtoCreate dto : dtoliste) {
			liste.add(new Module(dto.getId(), dto.getNom(),dto.getMatiere()));
		}
		return liste;
	}

	@Override
	public ModuleDtoCreate entiteToDto(Module entite) {
		if(entite==null) {
			return null;
		}
		ModuleDtoCreate dto = new ModuleDtoCreate(entite.getId(),entite.getNom(), entite.getIdModule());
		return dto;
	}

	@Override
	public List<ModuleDtoCreate> listEntiteToDto(List<Module> entite) {
		if(entite==null) {
			return null;
		}
		List<ModuleDtoCreate> liste = new ArrayList<ModuleDtoCreate>();
		for (Module e : entite) {
			liste.add(new ModuleDtoCreate(e.getId(),e.getNom(), e.getIdModule()));
		}
		return liste;
	}
	

}
