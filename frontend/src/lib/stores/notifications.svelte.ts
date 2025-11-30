/**
 * Global notification store for toast messages.
 * Supports error, success, warning, and info notifications with auto-dismiss.
 */

export interface Notification {
	id: string;
	type: 'error' | 'success' | 'warning' | 'info';
	message: string;
	errorId?: string;
	errorCode?: string;
	timestamp: number;
}

// Reactive state using Svelte 5 $state rune
export const notificationState = $state({
	notifications: [] as Notification[]
});

// Auto-dismiss timeouts (ms)
const DISMISS_TIMES: Record<Notification['type'], number> = {
	error: 10000,
	warning: 8000,
	success: 4000,
	info: 5000
};

/**
 * Add a notification to the queue
 */
function addNotification(
	type: Notification['type'],
	message: string,
	errorId?: string,
	errorCode?: string
): string {
	const id = crypto.randomUUID();
	const notification: Notification = {
		id,
		type,
		message,
		errorId,
		errorCode,
		timestamp: Date.now()
	};

	notificationState.notifications.push(notification);

	// Auto-dismiss after timeout
	setTimeout(() => {
		dismiss(id);
	}, DISMISS_TIMES[type]);

	return id;
}

/**
 * Show an error notification
 */
export function notifyError(message: string, errorId?: string, errorCode?: string): string {
	return addNotification('error', message, errorId, errorCode);
}

/**
 * Show a success notification
 */
export function notifySuccess(message: string): string {
	return addNotification('success', message);
}

/**
 * Show a warning notification
 */
export function notifyWarning(message: string): string {
	return addNotification('warning', message);
}

/**
 * Show an info notification
 */
export function notifyInfo(message: string): string {
	return addNotification('info', message);
}

/**
 * Dismiss a specific notification
 */
export function dismiss(id: string): void {
	const index = notificationState.notifications.findIndex((n) => n.id === id);
	if (index !== -1) {
		notificationState.notifications.splice(index, 1);
	}
}

/**
 * Dismiss all notifications
 */
export function dismissAll(): void {
	notificationState.notifications = [];
}
