# Sales Taxes

[![CI](https://github.com/HoangBachLeLe/SalesTaxes/actions/workflows/main.yml/badge.svg)](https://github.com/HoangBachLeLe/SalesTaxes/actions/workflows/main.yml)
[![Code Grade](https://api.codiga.io/project/30864/score/svg)](https://www.codiga.io)
[![Code Grade](https://api.codiga.io/project/30864/status/svg)](https://www.codiga.io)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/dde9bb18bb5a4abaa529e7cc51aacdba)](https://app.codacy.com/gh/HoangBachLeLe/SalesTaxes?utm_source=github.com&utm_medium=referral&utm_content=HoangBachLeLe/SalesTaxes&utm_campaign=Badge_Grade_Settings)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/cbe4a2abbaac4ec8a63d6763520943ed)](https://www.codacy.com/gh/HoangBachLeLe/SalesTaxes/dashboard?utm_source=github.com&utm_medium=referral&utm_content=HoangBachLeLe/SalesTaxes&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/HoangBachLeLe/SalesTaxes/branch/main/graph/badge.svg?token=C46GYP2OXE)](https://codecov.io/gh/HoangBachLeLe/SalesTaxes)

The application is available on heroku: https://sales-taxes.herokuapp.com/

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical 
products that are exempt. Import duty is an additional sales tax applicable on all imported goods 
at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price 
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes 
paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains 
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...

### INPUT:

Input 1:</br>
1 book at 12.49</br>
1 music CD at 14.99</br>
1 chocolate bar at 0.85</br>

Input 2:</br>
1 imported box of chocolates at 10.00</br>
1 imported bottle of perfume at 47.50</br>

Input 3:</br>
1 imported bottle of perfume at 27.99</br>
1 bottle of perfume at 18.99</br>
1 packet of headache pills at 9.75</br>
1 box of imported chocolates at 11.25</br>

### OUTPUT

Output 1:</br>
1 book : 12.49</br>
1 music CD: 16.49</br>
1 chocolate bar: 0.85</br>
Sales Taxes: 1.50</br>
Total: 29.83</br>

Output 2:</br>
1 imported box of chocolates: 10.50</br>
1 imported bottle of perfume: 54.65</br>
Sales Taxes: 7.65</br>
Total: 65.15</br>

Output 3:</br>
1 imported bottle of perfume: 32.19</br>
1 bottle of perfume: 20.89</br>
1 packet of headache pills: 9.75</br>
1 imported box of chocolates: 11.85</br>
Sales Taxes: 6.70</br>
Total: 74.68</br>

## Preview
[See template on GitHub Pages](https://hoangbachlele.github.io/SalesTaxes/src/main/resources/templates/index.html)

![Preview](./preview.png)

## How to run the application?
In order to run the application you need to have Docker and docker-compose installed on your machine. Execute the following command:

```sh
docker-compose up
```

Then open the web page `http://localhost:8080` in a browser.
