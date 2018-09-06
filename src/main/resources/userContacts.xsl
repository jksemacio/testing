<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="A4-portrait" page-height="29.7cm" page-width="21.0cm"
					margin="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:value-of select="User/@id" />. <xsl:value-of select="User/Email" />
					</fo:block>
					<fo:block>
						<fo:table>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											Name
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											Number
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											Country
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each select="User/Contacts/Contact">
									<fo:table-row>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="Name" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="Number" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="Country" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>