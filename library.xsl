<?xml version = "1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">

<!--  VARIABLE      -->
        <xsl:variable name="header">
            <tr bgcolor="#9acd32">
                <th scope="col">ID</th>
                <th scope="col">Firstname</th>
                <th scope="col">Lastname</th>
                <th scope="col">Phone</th>
                <th scope="col">Street</th>
                <th scope="col">City</th>
                <th scope="col">Country</th>
            </tr>
        </xsl:variable>


        <html>
          <head>
              <meta charset="utf-8"/>
              <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
            </head>
            <body>
                <div class="container">
                    <div class="col-12">
                    <h2>Customers</h2>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <xsl:copy-of select="$header"/>
                    </thead>
                    <xsl:for-each select="library/users/customers/customer">
                        <tr>
                            <xsl:if test="matches(@customer_id,'A[0-9]{6}')">
                                <th scope="row" style="background-color:blue;"><xsl:value-of select="@customer_id"/></th>
                            </xsl:if>
                            <xsl:if test="matches(@customer_id,'B[0-9]{6}')">
                                <th scope="row" style="background-color:pink;"><xsl:value-of select="@customer_id"/></th>
                            </xsl:if>
                            <td><xsl:value-of select="firstname"/></td>
                            <td><xsl:value-of select="lastname"/></td>
                            <td><xsl:value-of select="phone"/></td>
                            <td><xsl:value-of select="address/street"/></td>
                            <td><xsl:value-of select="address/city"/></td>
                            <td><xsl:value-of select="address/country"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
                    <h2>Librarians</h2>
                    <table class="table table-hover">
                        <thead class="thead-dark">
                            <xsl:copy-of select="$header"/>
                        </thead>
                        <xsl:for-each select="library/users/librarians/librarian">
                            <tr>
                                <xsl:if test="matches(@librarian_id,'A[0-9]{4}')">
                                    <th scope="row" style="background-color:blue;"><xsl:value-of select="@librarian_id"/></th>
                                </xsl:if>
                                <xsl:if test="matches(@librarian_id,'B[0-9]{4}')">
                                    <th scope="row" style="background-color:pink;"><xsl:value-of select="@librarian_id"/></th>
                                </xsl:if>
                                <td><xsl:value-of select="firstname"/></td>
                                <td><xsl:value-of select="lastname"/></td>
                                <td><xsl:value-of select="phone"/></td>
                                <td><xsl:value-of select="address/street"/></td>
                                <td><xsl:value-of select="address/city"/></td>
                                <td><xsl:value-of select="address/country"/></td>
                            </tr>
                        </xsl:for-each>
                    </table>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="table-responsive col-sm-4">
                            <h2>Publishers</h2>
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr bgcolor="#9acd32">
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Country</th>
                                    </tr>
                                </thead>
                                <xsl:for-each select="library/publishers/publisher">
                                    <tr>
                                        <th scope="row" style="background-color:brown;"><xsl:value-of select="@publisher_id"/></th>
                                        <td><xsl:value-of select="name"/></td>
                                        <td><xsl:value-of select="country"/></td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                        </div>
                            <div class="table-responsive col-md-6">
                                <h2>Authors</h2>
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr bgcolor="#9acd32">
                                        <th scope="col">ID</th>
                                        <th scope="col">firstname</th>
                                        <th scope="col">lastname</th>
                                        <th scope="col">Country</th>
                                    </tr>
                                </thead>
                                <xsl:for-each select="library/authors/author">
                                    <xsl:sort select="lastname"/>
                                    <tr>
                                        <th scope="row" style="background-color:aqua;"><xsl:value-of select="@author_id"/></th>
                                        <td ><xsl:value-of select="firstname"/></td>
                                        <td ><xsl:value-of select="lastname"/></td>
                                        <td ><xsl:value-of select="country"/></td>
                                    </tr>
                                </xsl:for-each>
                            </table>
                            </div>
                    </div>
                    <div class="col-12">
                        <h2>Books</h2>
                        <table class="table table-hover">
                            <thead class="thead-dark">
                                <tr bgcolor="#9acd32">
                                    <th scope="col">ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Pages</th>
                                    <th scope="col">Yesr</th>
                                    <th scope="col">ISBN</th>
                                    <th scope="col">Publisher</th>
                                    <th scope="col">Author</th>
                                </tr>
                            </thead>
                            <xsl:for-each select="library/books/book">
                                <tr>
                                    <th scope="row" style="background-color:navy;color:white;"><xsl:value-of select="@book_id"/></th>
                                    <td><xsl:value-of select="title"/></td>
                                    <td><xsl:value-of select="pages"/></td>
                                    <td><xsl:value-of select="year"/></td>
                                    <td><xsl:value-of select="isbn"/></td>
                                    <td style="background-color:brown;"><xsl:value-of select="book_publisher/@pub_id"/></td>
                                    <td style="background-color:aqua;"><xsl:value-of select="book_author/@aut_id"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </div>
                    <div class="col-6">
                        <h2>Borrowings</h2>
                        <table class="table table-hover">
                            <thead class="thead-dark">
                                <tr bgcolor="#9acd32">
                                    <th scope="col">ID</th>
                                    <th scope="col">Book ID</th>
                                    <th scope="col">Customer ID</th>
                                    <th scope="col">Librarian ID</th>
                                </tr>
                            </thead>
                            <xsl:for-each select="library/borrowings/borrowing">
                                <tr>
                                    <th scope="row" style="background-color:navy;color:white;"><xsl:value-of select="@bor_id"/></th>
                                    <td style="background-color:navy;color:white;"><xsl:value-of select="borrowed_book/@id_book"/></td>
                                    <xsl:if test="matches(borrowing_user/@id_user,'A[0-9]{6}')">
                                        <th scope="row" style="background-color:blue;"><xsl:value-of select="borrowing_user/@id_user"/></th>
                                    </xsl:if>
                                    <xsl:if test="matches(borrowing_user/@id_user,'B[0-9]{6}')">
                                        <th scope="row" style="background-color:pink;"><xsl:value-of select="borrowing_user/@id_user"/></th>
                                    </xsl:if>
                                    <xsl:if test="matches(worker/@id_librarian,'A[0-9]{4}')">
                                        <th scope="row" style="background-color:blue;"><xsl:value-of select="worker/@id_librarian"/></th>
                                    </xsl:if>
                                    <xsl:if test="matches(worker/@id_librarian,'B[0-9]{4}')">
                                        <th scope="row" style="background-color:pink;"><xsl:value-of select="worker/@id_librarian"/></th>
                                    </xsl:if>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>