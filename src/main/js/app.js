import React from 'react';
import { render } from "react-dom";
import {
  BrowserRouter,
  Switch,
  Route,
} from "react-router-dom";
import Home from './components/Home';
import Second from './components/Second';

const routes = () => (
	<BrowserRouter>
		<Switch>
			<Route path="/" render={routeProps => <Home {...routeProps} />} />
			<Route path="/second" render={routeProps => <Second {...routeProps} />} />
		</Switch>
	</BrowserRouter>
)

render(
	routes(),
	document.getElementById('react')
)

