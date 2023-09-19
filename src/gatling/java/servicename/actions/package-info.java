/**
 * В этом пакете хранится код действий пользоватей.
 * <p>
 * Action (одно действие пользователя) обычно представляют собой запросы (HTTP, WebSocket, JMS, MQTT…), которые будут отправлены во время моделирования. Любое действие, которое будет выполнено, будет вызываться с помощью exec.
 * <p>
 * Пример структуры:
 *<p> actions:
 *<ul>
 *	<li> authorization:
 *	<ul>
 *		<li>Login.java (возможные методы):
 *			<ul><li>Login.withPassword()</li>
 *			<li>Login.withKey()</li></ul>
 *		</li>
 *	</ul>
 *	</li>
 *	<li> orders:
 *	<ul>
 *		<li>Login.java (возможные методы):
 *			<ul><li>CreateOrder.buyRedSocks(5)</li>
 *			<li>CreateOrder.buyBlackHat(1)</li></ul>
 *		</li>
 *		<li> DeleteOrder.java </li>
 *	</ul>
 *	</li>
 *</ul>
 * Синонимы: вызов, запрос
 * @since 2023-08-11
 */
package servicename.actions;