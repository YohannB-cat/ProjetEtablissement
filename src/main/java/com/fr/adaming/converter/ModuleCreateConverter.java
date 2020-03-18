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
			Module entite = new Module(dto.getId(), dto.getNom(), dto.getMatiere());
			return entite;

		}
		return null;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDtoCreate> dtoliste) {
		if (dtoliste == null) {
			return null;
		}
		List<Module> liste = new ArrayList<Module>();
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
			ModuleDtoCreate dto = new ModuleDtoCreate(entite.getId(), entite.getNom(), entite.getMatieres());
			return dto;
		}
		return null;
	}

	@Override
	public List<ModuleDtoCreate> listEntiteToDto(List<Module> entite) {
		if (entite != null) {
			List<ModuleDtoCreate> liste = new ArrayList<ModuleDtoCreate>();
			for (Module e : entite) {
				if (e.getNom() != null) {
					liste.add(new ModuleDtoCreate(e.getId(), e.getNom(), e.getMatieres()));
				}
			}
			return liste;

		}
		return null;
	}

}
