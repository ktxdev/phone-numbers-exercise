import React, { useEffect, useState } from 'react'
import { Search } from '@mui/icons-material';
import { Button, Container, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material'
import { Box } from '@mui/system';
import { getAllCountries } from '../api/lib/country';

const Filter = ({onFilter}) => {

    useEffect(() => {
        fetchAllCountries();
    }, [])
    
    const [countries, setCountries] = useState([])

    const [filters, setFilters] = useState({
        countryCode: 'ALL',
        state: 'ALL',
        phone: ''
    })

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFilters({...filters, [name]: value})
    }

    const fetchAllCountries = async () => {
        const response = await getAllCountries();
        if(response.status === 200) {
            setCountries(response.data)
        }
    }

    const handleSearch = () => {
        const countryCode = filters.countryCode === 'ALL' ? '': filters.countryCode;
        onFilter({...filters, countryCode});
    }

    return (
        <Container maxWidth="xl">
            <Box sx={{ my: 5, display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: 2 }}>
                <FormControl fullWidth>
                    <InputLabel id="country-select-label">Country</InputLabel>
                    <Select
                        labelId="country-select-label"
                        id="country-select"
                        value={filters.countryCode}
                        label="Country"
                        name='countryCode'
                        size='small'
                        onChange={handleChange}
                    >
                        <MenuItem value={"ALL"}>All Countries</MenuItem>
                        {
                            countries.map(c => <MenuItem key={c.code} value={c.code}>{c.name}</MenuItem>)
                        }
                    </Select>
                </FormControl>

                <FormControl fullWidth>
                    <InputLabel id="state-select-label">Phone Number State</InputLabel>
                    <Select
                        labelId="state-select-label"
                        id="state-select"
                        value={filters.state}
                        label="Phone Number State"
                        name='state'
                        size='small'
                        onChange={handleChange}
                    >
                        <MenuItem value={'ALL'}>All</MenuItem>
                        <MenuItem value={"VALID"}>Valid</MenuItem>
                        <MenuItem value={"INVALID"}>Invalid</MenuItem>
                    </Select>
                </FormControl>

                <FormControl fullWidth>
                    <TextField 
                        id="phone" 
                        size='small'
                        name='phone' 
                        value={filters.phone}
                        onChange={handleChange} 
                        label="Search By Phone Number" 
                        variant="outlined" />
                </FormControl>

                <Box sx={{ flexShrink: 0 }}>
                    <Button onClick={handleSearch} variant="contained" startIcon={<Search />}>Search</Button>
                </Box>
            </Box>
        </Container>
    )
}

export default Filter