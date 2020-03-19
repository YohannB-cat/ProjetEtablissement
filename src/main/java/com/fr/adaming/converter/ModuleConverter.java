package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ModuleDto;
import com.fr.adaming.entity.Module;

@Component
public class ModuleConverter implements IConverter<Module, ModuleDto> {

	@Override
	public Module dtoToEntite(ModuleDto dto) {
		if (dto != null && dto.getNom() != null) {
			return new Module(0, dto.getNom(), dto.getMatiere());
		}
		return null;
	}

	@Override
	public List<Module> listDtoToEntite(List<ModuleDto> dtoliste) {
		List<Module> liste = new ArrayList<>();
		if (dtoliste == null) {
			return liste;
		}
		
		for (ModuleDto dto : dtoliste) {
			if (dto.getNom() != null) {
				liste.add(new Module(0, dto.getNom(), dto.getMatiere()));
			}
		}
		return liste;
	}

	@Override
	public ModuleDto entiteToDto(Module entite) {
		if (entite != null && entite.getNom() != null) {
			return new ModuleDto(entite.getNom(), entite.getMatieres());

		}
		return null;
	}

	@Override
	public List<ModuleDto> listEntiteToDto(List<Module> entite) {
		List<ModuleDto> liste = new ArrayList<>();
		if (entite == null) {
			return liste;
		}
		
		for (Module e : entite) {
			if (e.getNom() != null) {
				liste.add(new ModuleDto(e.getNom(), e.getMatieres()));
			}

		}
		return liste;
	}

}
