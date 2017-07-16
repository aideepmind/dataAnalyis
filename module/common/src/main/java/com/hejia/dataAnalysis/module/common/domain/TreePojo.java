package com.hejia.dataAnalysis.module.common.domain;

import java.util.List;

public class TreePojo {
	private Object id;
	private String text;
	private State state;
	private String icon;
	
	private List<TreePojo> children;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



	public List<TreePojo> getChildren() {
		return children;
	}

	public void setChildren(List<TreePojo> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public class State {
		private boolean opened;
		private boolean selected;
		private boolean loaded;
		private boolean disabled;
		
		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isOpened() {
			return opened;
		}

		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public boolean isLoaded() {
			return loaded;
		}

		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	
	  
		
	
	}
}
