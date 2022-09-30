import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import { Divider } from '@mui/material';

const Dogs = () => {

    const [dogs, setDogs] = useState([]);
    useEffect(() => {
        fetch(
            "/api/dog")
                        .then((res) => res.json())
                        .then((json) => {
                            setDogs(json)
                        })
    }, []);

    const renderDogImages = () => {
        return (
            <ImageList sx={{ width: '100%', height: 450 }} cols={3} rowHeight={'auto'}>
                {dogs.map((dog) => (
                    <ImageListItem key={dog.img}>
                    <img
                        src={`${dog.img}?w=164&h=164&fit=crop&auto=format`}
                        srcSet={`${dog.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                        alt={dog.breed}
                        loading="lazy"
                    />
                    </ImageListItem>
                ))}
            </ImageList>
        )
    }

    return (
    <React.Fragment>
        <Divider style={{marginTop: 50}} />
        <Typography variant='h5'>
            Dogs! <span style={{ fontSize: 14 }}>(Also a good GET Endpoint test)</span>
        </Typography>
        <div>
            {!dogs || dogs.length === 0 ? 'Loading some pups...' : renderDogImages()}
        </div>
    </React.Fragment>
        );
    
}

Dogs.propTypes = {
}

export default Dogs;