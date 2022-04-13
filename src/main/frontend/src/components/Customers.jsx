import { Container, Table, TableBody, TableCell, TableHead, TableRow, styled, tableCellClasses } from '@mui/material'
import React from 'react'

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
        fontSize: 16
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

const Customers = ({ data }) => {
    return (
        <Container maxWidth="xl">
            <Table sx={{ width: 1 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell>Customer ID</StyledTableCell>
                        <StyledTableCell>Customer Name</StyledTableCell>
                        <StyledTableCell align="right">Phone Number</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((row) => (
                        <StyledTableRow
                            key={row.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <StyledTableCell component="th" scope="row">
                                {row.id}
                            </StyledTableCell>
                            <StyledTableCell>{row.name}</StyledTableCell>
                            <StyledTableCell align="right">{row.phone}</StyledTableCell>
                        </StyledTableRow>
                    ))}
                </TableBody>
            </Table>
        </Container>
    )
}

export default Customers