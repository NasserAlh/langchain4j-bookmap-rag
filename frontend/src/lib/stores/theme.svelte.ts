// Use a class to create a reactive object that we can export
class ThemeStore {
	darkMode = $state(false);

	constructor() {
		this.initTheme();
	}

	initTheme() {
		if (typeof window !== 'undefined') {
			const saved = localStorage.getItem('theme');
			if (saved) {
				this.darkMode = saved === 'dark';
			} else {
				// Check system preference
				this.darkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
			}
			this.applyTheme();
		}
	}

	private applyTheme() {
		if (typeof window !== 'undefined') {
			const html = document.documentElement;
			if (this.darkMode) {
				html.classList.add('dark');
			} else {
				html.classList.remove('dark');
			}
			localStorage.setItem('theme', this.darkMode ? 'dark' : 'light');
		}
	}

	toggle() {
		this.darkMode = !this.darkMode;
		this.applyTheme();
	}

	set(value: boolean) {
		this.darkMode = value;
		this.applyTheme();
	}

	get() {
		return this.darkMode;
	}
}

export const themeStore = new ThemeStore();

// Exported functions for backwards compatibility
export function initTheme() {
	themeStore.initTheme();
}

export function toggleTheme() {
	themeStore.toggle();
}

export function getDarkMode() {
	return themeStore.get();
}

export function setDarkMode(value: boolean) {
	themeStore.set(value);
}

// Initialize theme when module loads
initTheme();
