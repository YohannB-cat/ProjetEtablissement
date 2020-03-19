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
		if (dto != null && dto.getNom() != null) {
			return new Module(dto.getId(), dto.getNom(), dto.getMatiere());

		}
		return null;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDtoCreate> dtoliste) {
		List<Module> liste = new ArrayList<>();
		if (dtoliste == null) {
			return liste;
		}
		for (ModuleDtoCreate dto : dtoliste) {
			if (dto.getNom() != null) {
				liste.add(new Module(dto.getId(), dto.getNom(), dto.getMatiere()));
			}
		}
		return liste;
	}

	@Override
	public ModuleDtoCreate entiteToDto(Module entite) {
		if (entite != null && entite.getNom() != null) {
			return new ModuleDtoCreate(entite.getId(), entite.getNom(), entite.getMatieres());
		}
		return null;
	}

	@Override
	public List<ModuleDtoCreate> listEntiteToDto(List<Module> entite) {
		List<ModuleDtoCreate> liste = new ArrayList<>();
		if (entite == null) {
			return liste ;
		}
			for (Module e : entite) {
				if (e.getNom() != null) {
					liste.add(new ModuleDtoCreate(e.getId(), e.getNom(), e.getMatieres()));
				}
			}
			return liste;
		}
	}

