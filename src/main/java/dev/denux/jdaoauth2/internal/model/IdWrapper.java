package dev.denux.jdaoauth2.internal.model;

import lombok.Getter;

@Getter
public class IdWrapper {

	private long id;

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
