/**
 * This package contains the controllers (called view-parts).
 * 
 * The {@link asteroids.ctrl.IViewPart} interface has to be implemented by
 * every class whose object should act as controllers. The 
 * {@link asteroids.ctrl.ViewPartBase} abstract base class should be used by
 * actual view part implementation (such as the
 * {@link asteroids.ctrl.SpaceshipViewPart}). It implements methods common
 * to all view parts.
 * 
 * The {@link asteroids.ctrl.ViewPartFactory} is responsible for creating
 * the {@link asteroids.ctrl.IViewPart} corresponding to a given model
 * element.
 * 
 * @author Frank Grimm
 *
 */
package asteroids.ctrl;