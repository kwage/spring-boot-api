import React from 'react';
import PropTypes from 'prop-types';

const Second = ({ test }) => (
    <div>We are home! {test}</div>
)

Second.propTypes = {
    test: PropTypes.string
}

export default Second;