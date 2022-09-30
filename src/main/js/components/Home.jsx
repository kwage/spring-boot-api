import React from 'react';
import PropTypes from 'prop-types';
import CommonToolbar from './common/CommonToolbar';
import { Divider, Link, Typography } from '@mui/material';
import Dogs from './Dogs';

const Home = () => (
    
    <React.Fragment>
        <CommonToolbar />
        <div style={{padding: 20}}>
            <div>
                <Typography variant="h4">Welcome!</Typography>
                <Typography variant="subtitle1">This is a simplistic React.js web app to assist in the Disney API Interview.</Typography>
            </div>
            <div style={{ marginTop: 20 }}>
                <Typography variant="h5">API Notes</Typography>
            </div>
            <Divider />
            <div style={{ marginTop: 10 }}>
                <Typography variant="h5">Dogs (URL: <Link href="/api/dog">/api/dog</Link>)</Typography>
                <Typography variant="h6">GET - Get All Dogs - gets all dogs with appropriate properties</Typography>
                <p>Available Filters on Name, Breed, and Image URL (e.g. <Link href="/api/dog?breed=Pug">/api/dog?breed=Pug</Link>)</p>
                <Typography variant="h6">GET - Get Dog By Id - gets specific dog from supplied id</Typography>
                <p>Example Request: <Link href="/api/dog/23">/api/dog/23</Link> (where 23 is the id of the dog)</p>
                <Typography variant="h6">GET - Get Dog By Breeds - gets all dogs ordered by breeds</Typography>
                <p>Example Request: <Link href="/api/dog/breeds">/api/dog/breeds</Link></p>
                <Typography variant="h6">POST - Create Dog - creates a new dog object</Typography>
                <p>Required fields: image link (img) and a breed type (breed)</p>
                <Typography variant="h6">PATCH - Update Dog By Id - updates a dog object of the supplied id in the path parameter</Typography>
                <p>Example Request: <Link href="/api/dog/23">/api/dog/23</Link> (where 23 is the id of the dog)</p>
                <p>Optional Fields: name, age, img. You cannot update likes or dislikes from this endpoint (See user API)</p>
                <Typography variant="h6">DELETE - Delete Dog By Id - deletes a specified dog from the database</Typography>
                <p>Example Request: <Link href="/api/dog/23">/api/dog/23</Link> (where 23 is the id of the dog)</p>
            </div>
            <Divider />
            <div style={{ marginTop: 10 }}>
                <Typography variant="h5">Users (URL: <Link href="/api/dog">/api/user</Link>)</Typography>
                <Typography variant="h6">GET - Get User By Id - gets specific user from supplied id</Typography>
                <p>Example Request: <Link href="/api/user/1">/api/user/1</Link> (where 1 is the id of the user)</p>
                <p>Returns: Full user object with liked and disliked dogs</p>
                <Typography variant="h6">POST - Create User - creates a new user object</Typography>
                <p>Required fields: a username (username)</p>
                <Typography variant="h6">PUT - User Like Dog By Id - likes a specific dog and associates with specific user</Typography>
                <p>Example Request: <Link href="/api/user/1/like">/api/user/1/like</Link> (where 1 is the id of the user)</p>
                <p>Required Fields: dogId (where this is the id of the liked dog)</p>
                <Typography variant="h6">PUT - User Dislike Dog By Id - dislikes a specific dog and associates with specific user</Typography>
                <p>Example Request: <Link href="/api/user/1/dislike">/api/user/1/dislike</Link> (where 1 is the id of the user)</p>
                <p>Required Fields: dogId (where this is the id of the disliked dog)</p>
            </div>
            <div style={{ marginTop: 10 }}>
                <Dogs />
            </div>
        </div>
    </React.Fragment>
)

Home.propTypes = {
}

export default Home;